package nl.han.aim.oosevt.lamport.data.dao.area;

import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AreaDAOImpl implements AreaDAO {
    private final static Logger LOGGER = Logger.getLogger(AreaDAOImpl.class.getName());

    @Override
    public void createArea(String name, double longitude, double latitude, int radius) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createArea(?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setDouble(2, longitude);
            statement.setDouble(3, latitude);
            statement.setInt(4, radius);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public List<Area> getAreas() {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getAreas()");
             ResultSet resultSet = statement.executeQuery()) {

            List<Area> foundAreas = new ArrayList<>();
            while (resultSet.next()) {
                foundAreas.add(new Area(
                        resultSet.getInt("area_id"),
                        resultSet.getString("area_name"),
                        resultSet.getDouble("longitude"),
                        resultSet.getDouble("latitude"),
                        resultSet.getInt("radius")));
            }
            return foundAreas;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Area getAreaById(int areaId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getAreaById(?)")) {
            statement.setInt(1, areaId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Area(
                            resultSet.getInt("area_id"),
                            resultSet.getString("area_name"),
                            resultSet.getDouble("longitude"),
                            resultSet.getDouble("latitude"),
                            resultSet.getInt("radius"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
        return null;
    }

    @Override
    public void updateArea(int areaId, String name, double longitude, double latitude, int radius) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateArea(?, ?, ?, ?, ?)")) {
            statement.setInt(1, areaId);
            statement.setString(2, name);
            statement.setDouble(3, longitude);
            statement.setDouble(4, latitude);
            statement.setInt(5, radius);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public void deleteArea(int areaId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteArea(?)")) {
            statement.setInt(1, areaId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }
}
