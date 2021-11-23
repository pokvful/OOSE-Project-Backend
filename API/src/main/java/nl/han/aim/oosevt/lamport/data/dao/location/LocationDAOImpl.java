package nl.han.aim.oosevt.lamport.data.dao.location;

import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    private InterventionDAO interventionDAO;

    private Location locationFromResultSet(ResultSet resultSet) {
        try {
            int locationId = resultSet.getInt("location_id");
            return new Location(
                    locationId,
                    resultSet.getString("location_name"),
                    resultSet.getInt("delay"),
                    resultSet.getDouble("longitude"),
                    resultSet.getDouble("latitude"),
                    resultSet.getInt("radius"),
                    new Area(
                            resultSet.getInt("area_id"),
                            resultSet.getString("area_name"),
                            resultSet.getDouble("area_longitude"),
                            resultSet.getDouble("area_latitude"),
                            resultSet.getInt("area_radius")
                    ),
                    interventionDAO.getInterventionsByLocationId(locationId)
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void createLocation(String name, int delay, double longitude, double latitude, int radius, int areaId, List<Integer> linkedInterventions) {
        try (Connection connection = DriverManager.getConnection(connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL createLocation(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, delay);
            statement.setDouble(3, longitude);
            statement.setDouble(4, latitude);
            statement.setInt(5, radius);
            statement.setInt(6, areaId);
            statement.setString(7, linkedInterventions.stream().map(Object::toString).collect(Collectors.joining(",")));

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public Location getLocationById(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getLocationById(?)")) {
            statement.setInt(1, locationId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return locationFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Location> getLocations() {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getLocations()");
             ResultSet resultSet = statement.executeQuery()) {
            List<Location> foundLocations = new ArrayList<>();
            while (resultSet.next()) {
                final Location foundLocation = locationFromResultSet(resultSet);
                foundLocations.add(foundLocation);
            }
            return foundLocations;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateLocation(int locationId, String name, int delay, double longitude, double latitude, int radius, int areaId, List<Integer> linkedInterventions) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateLocation(?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, locationId);
            statement.setString(2, name);
            statement.setInt(3, delay);
            statement.setDouble(4, longitude);
            statement.setDouble(5, latitude);
            statement.setInt(6, radius);
            statement.setInt(7, areaId);
            statement.setString(8, linkedInterventions.stream().map(Object::toString).collect(Collectors.joining(",")));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLocation(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteLocation(?)")) {
            statement.setInt(1, locationId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> getListOfIdsFromString(final String input) {
        if(input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays
                .stream(input.split(","))
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
