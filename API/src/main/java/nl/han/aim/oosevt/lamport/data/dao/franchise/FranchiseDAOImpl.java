package nl.han.aim.oosevt.lamport.data.dao.franchise;

import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class FranchiseDAOImpl implements FranchiseDAO {
    private final static Logger LOGGER = Logger.getLogger(FranchiseDAOImpl.class.getName());

    @Override
    public void createFranchise(String name) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createFranchise(?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public Franchise getFranchiseById(int franchiseId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getFranchiseById(?)")) {
            statement.setInt(1, franchiseId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Franchise(
                            resultSet.getInt("franchise_id"),
                            resultSet.getString("franchise_name"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
        return null;
    }

    @Override
    public List<Franchise> getFranchises() {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getFranchises()");
             ResultSet resultSet = statement.executeQuery()) {

            List<Franchise> foundFranchises = new ArrayList<>();
            while (resultSet.next()) {
                foundFranchises.add(new Franchise(
                        resultSet.getInt("franchise_id"),
                        resultSet.getString("franchise_name")));
            }
            return foundFranchises;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public void updateFranchise(int franchiseId, String name) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateFranchise(?, ?)")) {
            statement.setInt(1, franchiseId);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public void deleteFranchise(int franchiseId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteFranchise(?)")) {
            statement.setInt(1, franchiseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }
}

