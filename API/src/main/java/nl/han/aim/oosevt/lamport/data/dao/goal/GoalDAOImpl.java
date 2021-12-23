package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.ProfileQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.data.entity.*;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GoalDAOImpl implements GoalDAO {

    private static final Logger LOGGER = Logger.getLogger(GoalDAOImpl.class.getName());

    @Override
    public void createGoal(String name, List<ProfileQuestionRequestDTO> questions) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createGoal(?)")) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    addProfileQuestionToGoal(resultSet.getInt("goal_id"), questions, connection);
                }
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "createGoal::A database error occurred!", e);
        }
    }

    @Override
    public Goal getGoalById(int goalId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getGoalById(?)")) {
            statement.setInt(1, goalId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return (goalFromResultSet(resultSet, connection));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getGoalById::A database error occurred!", e);
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
                getGoals.add(goalFromResultSet(resultSet, connection));
            }
            return getGoals;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getGoals::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public void updateGoal(int goalId, String name, List<ProfileQuestionRequestDTO> questions) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateGoal(?, ?)")) {
            statement.setInt(1, goalId);
            statement.setString(2, name);

            addProfileQuestionToGoal(goalId, questions, connection);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateGoal::A database error occurred!", e);
        }
    }

    @Override
    public void deleteGoal(int goalId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteGoal(?)")) {
            statement.setInt(1, goalId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "deleteGoal::A database error occurred!", e);
        }
    }

    public List<ProfileQuestion> getProfileQuestionFromGoalId(int profileQuestionId, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("CALL getProfileQuestionByGoalId(?)")) {
            statement.setInt(1, profileQuestionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<ProfileQuestion> profileQuestions = new ArrayList<>();

                while (resultSet.next()) {
                    profileQuestions.add(new ProfileQuestion(
                            resultSet.getInt("profile_question_id"),
                            resultSet.getString("question")
                    ));
                }

                return profileQuestions;
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "getProfileQuestionFromGoalId::A database error occurred!", e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getProfileQuestionFromGoalId::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    private Goal goalFromResultSet(ResultSet resultSet, Connection connection) {
        try {
            int goalId = resultSet.getInt("goal_id");
            return new Goal(
                    resultSet.getInt("goal_id"),
                    resultSet.getString("goal_name"),
                    getProfileQuestionFromGoalId(goalId, connection)
            );
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "goalFromResultSet::A database error occurred!", e);
        }
        return null;
    }

    private void addProfileQuestionToGoal(int goalId, List<ProfileQuestionRequestDTO> questions, Connection connection) {
        for (ProfileQuestionRequestDTO question : questions) {
            String questionText = question.getName();

            try (PreparedStatement statement = connection.prepareStatement("CALL addProfileQuestionToGoal(?, ?)")) {
                statement.setInt(1, goalId);
                statement.setString(2, questionText);

                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "addProfileQuestionToGoal::A database error occurred!", e);
            }
        }
    }
}
