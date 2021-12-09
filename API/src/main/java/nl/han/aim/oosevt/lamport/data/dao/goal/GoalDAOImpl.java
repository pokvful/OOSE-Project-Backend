package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.data.entity.Goal;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GoalDAOImpl implements GoalDAO {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void createGoal(String name) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createGoal(?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public Goal getGoalById(int goalId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getGoalById(?)")) {
            statement.setInt(1, goalId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Goal(
                            resultSet.getInt("goal_id"),
                            resultSet.getString("goal_name"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
        return null;
    }

    @Override
    public List<Goal> getGoals() {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getGoals()");
             ResultSet resultSet = statement.executeQuery()) {

            List<Goal> getGoals = new ArrayList<>();
            while (resultSet.next()) {
                getGoals.add(new Goal(
                        resultSet.getInt("goal_id"),
                        resultSet.getString("goal_name")));
            }
            return getGoals;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public void updateGoal(int goalId, String name) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateGoal(?, ?)")) {
            statement.setInt(1, goalId);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public void deleteGoal(int goalId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteGoal(?)")) {
            statement.setInt(1, goalId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
    }
}
