package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.ProfileQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.data.entity.*;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GoalDAOImpl implements GoalDAO {

    private static final Logger LOGGER = Logger.getLogger(GoalDAOImpl.class.getName());
    private final GoalDataMapper goalDataMapper;

    @Autowired
    public GoalDAOImpl(GoalDataMapper goalDataMapper) {
        this.goalDataMapper = goalDataMapper;
    }

    @Override
    public void createGoal(String name, List<ProfileQuestionRequestDTO> questions) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL createGoal(?)")) {
            statement.setString(1, name);

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
    public int getUserCountByGoalId(int goalId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getUserCountByGoalId   (?)")) {

            statement.setInt(1, goalId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt("count");
                }
                return 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getUserCountByGoalId::A database error occurred!", e);
        }
        return 0;
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

            statement.executeUpdate();

            addProfileQuestionToGoal(goalId, questions, connection);
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
        try (PreparedStatement statement = connection.prepareStatement("CALL getProfileQuestionsByGoalId(?)")) {
            statement.setInt(1, profileQuestionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<ProfileQuestion> profileQuestions = new ArrayList<>();

                while (resultSet.next()) {
                    profileQuestions.add(new ProfileQuestion(
                            resultSet.getInt("profile_question_id"),
                            resultSet.getString("question")));
                }

                return profileQuestions;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getProfileQuestionFromGoalId::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    private Goal goalFromResultSet(ResultSet resultSet, Connection connection) {
        final Goal goal = goalDataMapper.getFromResultSet(resultSet);
        goal.getProfileQuestions().addAll(getProfileQuestionFromGoalId(goal.getGoalId(), connection));

        return goal;
    }

    private void addProfileQuestionToGoal(int goalId, List<ProfileQuestionRequestDTO> questions, Connection connection) {
        for (ProfileQuestionRequestDTO question : questions) {
            try (PreparedStatement statement = connection.prepareStatement("CALL addProfileQuestionToGoal(?, ?)")) {
                statement.setInt(1, goalId);
                statement.setString(2, question.getName());

                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "addProfileQuestionToGoal::A database error occurred!", e);
            }
        }
    }
}
