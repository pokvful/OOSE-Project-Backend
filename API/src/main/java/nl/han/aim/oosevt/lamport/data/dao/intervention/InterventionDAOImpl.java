package nl.han.aim.oosevt.lamport.data.dao.intervention;

import nl.han.aim.oosevt.lamport.data.entity.*;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class InterventionDAOImpl implements InterventionDAO {

    private static final Logger LOGGER = Logger.getLogger(InterventionDAOImpl.class.getName());

    private void setAnswersForQuestion(int questionId, List<Answer> answers, Connection connection) {
        for (Answer answer : answers) {
            String answerText = answer.getAnswerText();

            try (PreparedStatement statement = connection.prepareStatement("CALL addAnswerToQuestion(?, ?)")) {
                statement.setInt(1, questionId);
                statement.setString(2, answerText);

                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "updateQuestion::A database error occurred!", e);
            }
        }
    }

    private List<Answer> getAnswersByQuestionId(int questionId, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("CALL getAnswersByQuestionId(?)")) {
            statement.setInt(1, questionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Answer> answers = new ArrayList<>();

                while (resultSet.next()) {
                    answers.add(new Answer(
                        0,
                        resultSet.getString("answer")
                    ));
                }

                return answers;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getInterventionsByLocationId::A database error occurred!", e);
        }

        return new ArrayList<>();
    }

    private Question getQuestionFromResultSet(ResultSet resultSet, Connection connection) {
        try {
            int questionId = resultSet.getInt("question_id");
            List<Answer> answers = getAnswersByQuestionId(questionId, connection);

            return new Question(
                    questionId,
                    "",
                    resultSet.getString("question"),
                    answers
            );

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getInterventionsByLocationId::A database error occurred!", e);
        }

        return null;
    }

    private List<Question> getQuestionsByQuestionnaireId(int interventionId, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("CALL getQuestionsByQuestionnaireInterventionId(?)")) {
            statement.setInt(1, interventionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Question> questions = new ArrayList<>();

                while (resultSet.next()) {
                    questions.add(getQuestionFromResultSet(resultSet, connection));
                }

                return questions;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getInterventionsByLocationId::A database error occurred!", e);
        }

        return new ArrayList<>();
    }

    private Intervention interventionFromResultSet(ResultSet resultSet, Connection connection) {
        try {
            String type = resultSet.getString("intervention_type");
            switch (type) {
                case "command":
                    return new Command(
                        resultSet.getInt("intervention_id"),
                        resultSet.getString("intervention_name"),
                        resultSet.getString("command")
                    );
                case "question":
                    int questionId = resultSet.getInt("intervention_id");
                    List<Answer> answers = getAnswersByQuestionId(questionId, connection);

                    return new Question(
                        questionId,
                        resultSet.getString("intervention_name"),
                        resultSet.getString("question"),
                        answers
                    );
                case "questionnaire":
                    int interventionId = resultSet.getInt("intervention_id");

                    return new Questionnaire(
                            interventionId,
                            resultSet.getString("intervention_name"),
                            getQuestionsByQuestionnaireId(interventionId, connection)
                    );
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "interventionFromResultSet::A database error occurred!", e);
        }
        return null;
    }

    @Override
    public List<Intervention> getInterventionsByLocationId(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getCommandsByLocationId(?)")) {
            statement.setInt(1, locationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Intervention> foundInterventions = new ArrayList<>();
                while (resultSet.next()) {
                    foundInterventions.add(interventionFromResultSet(resultSet, connection));
                }

                return foundInterventions;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getInterventionsByLocationId::A database error occurred!", e);
        }

        return new ArrayList<>();
    }

    @Override
    public void updateCommand(int id, String name, String command) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateCommand(?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, command);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public void createCommand(String name, String command) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createCommand(?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, command);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "createCommand::A database error occurred!", e);
        }
    }

    @Override
    public void updateQuestion(int id, String name, String question, List<Answer> answers) {
        try (
            Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL updateQuestion(?, ?, ?)")
        ) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, question);

            statement.executeUpdate();

            setAnswersForQuestion(id, answers, connection);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateQuestion::A database error occurred!", e);
        }
    }

    public void createQuestion(String name, String question, List<Answer> answers) {
        try (
            Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL createQuestion(?, ?)")
        ) {
            statement.setString(1, name);
            statement.setString(2, question);

            try (ResultSet resultSet = statement.executeQuery()) {
                int questionId = resultSet.getInt("question_id");

                setAnswersForQuestion(questionId, answers, connection);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "createQuestion::A database error occurred!", e);
        }
    }

    @Override
    public List<Intervention> getInterventions() {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getInterventions()")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Intervention> foundInterventions = new ArrayList<>();
                while (resultSet.next()) {
                    foundInterventions.add(interventionFromResultSet(resultSet, connection));
                }

                return foundInterventions;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getInterventionsByLocationId::A database error occurred!", e);
        }

        return new ArrayList<>();
    }

    @Override
    public Intervention getInterventionById(int id) {
        try (
             Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getInterventionById(?)")
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return interventionFromResultSet(resultSet, connection);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getInterventionsByLocationId::A database error occurred!", e);
        }

        return null;
    }
}
