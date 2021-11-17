package nl.han.aim.oosevt.lamport.data.dao.area;

import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AreaDAOImpl implements AreaDAO {

    @Override
    public void createArea(String name, double longitude, double latitude, int radius) {

        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL createArea(?, ?, ?, ?)")
        ) {
            statement.setString(1, name);
            statement.setDouble(2, longitude);
            statement.setDouble(3, latitude);
            statement.setInt(4, radius);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Area> getAreas() {

        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL getAreas()")
        ) {
            ResultSet resultSet = statement.executeQuery();

            List<Area> foundAreas = new ArrayList<>();
            while (resultSet.next()) {

                Area foundArea = new Area(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("latitude"),
                        resultSet.getDouble("longitude"),
                        resultSet.getInt("radius")
                );
                foundAreas.add(foundArea);
            }
            return foundAreas;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Area getArea(int areaId) {

        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL getArea(?)")
        ) {
            statement.setInt(1, areaId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Area(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("latitude"),
                        resultSet.getDouble("longitude"),
                        resultSet.getInt("radius")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateArea(int areaId, String name, double longitude, double latitude, int radius) {

        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL updateArea(?, ?, ?, ?, ?)")
        ) {
            statement.setInt(1, areaId);
            statement.setString(2, name);
            statement.setDouble(3, longitude);
            statement.setDouble(4, latitude);
            statement.setInt(5, radius);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void deleteArea(int areaId) {

        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL deleteArea(?)")
        ) {
            statement.setInt(1, areaId);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
