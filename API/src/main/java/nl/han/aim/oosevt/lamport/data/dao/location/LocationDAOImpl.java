package nl.han.aim.oosevt.lamport.data.dao.location;

import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class LocationDAOImpl implements LocationDAO {

    private static final Logger LOGGER = Logger.getLogger(LocationDAOImpl.class.getName());
    private final InterventionDAO interventionDAO;
    private final LocationDataMapper locationDataMapper;

    @Autowired
    public LocationDAOImpl(InterventionDAO interventionDAO, LocationDataMapper locationDataMapper) {
        this.interventionDAO = interventionDAO;
        this.locationDataMapper = locationDataMapper;
    }
    private Location locationFromResultSet(ResultSet resultSet) {
        final Location location = locationDataMapper.getFromResultSet(resultSet);
        location.getLinkedInterventions().addAll(interventionDAO.getInterventionsByLocationId(location.getId()));

        return location;
    }

    @Override
    public void createLocation(String name, int delay, double longitude, double latitude, int radius, int areaId, int franchiseId, List<Integer> linkedInterventions) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createLocation(?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, delay);
            statement.setDouble(3, longitude);
            statement.setDouble(4, latitude);
            statement.setInt(5, radius);
            statement.setInt(6, areaId);
            if(franchiseId == 0) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, franchiseId);
            }
            statement.setString(8, linkedInterventions.stream().map(Object::toString).collect(Collectors.joining(",")));

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "createLocation::A database error occurred!", e);
        }
    }

    @Override
    public Location getLocationById(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getLocationById(?)")) {
            statement.setInt(1, locationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return locationFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getLocationById::A database error occurred!", e);
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
            LOGGER.log(Level.SEVERE, "getLocations::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public void updateLocation(int locationId, String name, int delay, double longitude, double latitude, int radius, int areaId, int franchiseId, List<Integer> linkedInterventions) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateLocation(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, locationId);
            statement.setString(2, name);
            statement.setInt(3, delay);
            statement.setDouble(4, longitude);
            statement.setDouble(5, latitude);
            statement.setInt(6, radius);
            statement.setInt(7, areaId);
            if(franchiseId == 0) {
                statement.setNull(8, Types.INTEGER);
            } else {
                statement.setInt(8, franchiseId);
            }
            statement.setString(9, linkedInterventions.stream().map(Object::toString).collect(Collectors.joining(",")));

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateLocation::A database error occurred!", e);
        }
    }

    @Override
    public void deleteLocation(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteLocation(?)")) {
            statement.setInt(1, locationId);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "deleteLocation::A database error occurred!", e);
        }
    }
}
