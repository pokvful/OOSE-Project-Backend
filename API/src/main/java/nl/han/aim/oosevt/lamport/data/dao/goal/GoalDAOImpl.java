package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.data.entity.Goal;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class GoalDAOImpl implements GoalDAO {
    @Override
    public void createGoal(String name) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createGoal(?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Goal getGoalById(int goalId) {
        return null;
    }

    @Override
    public List<Goal> getGoals() {
        return null;
    }

    @Override
    public void updateGoal(int goalId, String name) {

    }

    @Override
    public void deleteGoal(int goalId) {

    }
}
