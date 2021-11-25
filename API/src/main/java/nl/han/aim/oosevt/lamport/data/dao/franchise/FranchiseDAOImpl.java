package nl.han.aim.oosevt.lamport.data.dao.franchise;

import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FranchiseDAOImpl implements FranchiseDAO {

    @Override
    public void createFranchise(String name) {

    }

    @Override
    public Franchise getFranchiseById(int franchiseId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getFranchiseById(?)")) {
            statement.setInt(1, franchiseId);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Franchise(
                        resultSet.getInt("franchise_id"),
                        resultSet.getString("franchise_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateFranchise(int franchiseId, String name) {

    }

    @Override
    public void deleteFranchise(int franchiseId) {

    }
}
