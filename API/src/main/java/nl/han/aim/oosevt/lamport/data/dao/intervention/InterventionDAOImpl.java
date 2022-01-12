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
            try (PreparedStatement statement = connection.prepareStatement("CALL addAnswerToQuestion(?, ?)")) {
                statement.setInt(1, questionId);
                statement.setString(2, answer.getAnswerText());

                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "setAnswersForQuestion::A database error occurred!", e);
            }
        }
    }

    private void setQuestionsForQuestionnaire(int questionnaireId, List<Question> questions, Connection connection) {
        for (Question question : questions) {
            try (PreparedStatement statement = connection.prepareStatement("CALL addQuestionToQuestionnaire(?, ?)")) {
                statement.setInt(1, questionnaireId);
                statement.setString(2, question.getQuestionText());

                try(ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        setAnswersForQuestion(resultSet.getInt("question_id"), question.getAnswers(), connection);
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "setQuestionsForQuestionnaire::A database error occurred!", e);
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
                        resultSet.getInt("answer_id"),
                        resultSet.getString("answer")
                    ));
                }

                return answers;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getAnswersByQuestionId::A database error occurred!", e);
        }

        return new ArrayList<>();
    }

    private Question getQuestionFromResultSet(ResultSet resultSet, Connection connection) {
        try {
            int questionId = resultSet.getInt("question_id");

            return new Question(
                    questionId,
                    "",
                    resultSet.getString("question"),
                    getAnswersByQuestionId(questionId, connection)
            );

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getQuestionFromResultSet::A database error occurred!", e);
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
            LOGGER.log(Level.SEVERE, "getQuestionsByQuestionnaireId::A database error occurred!", e);
        }

        return new ArrayList<>();
    }

    private Intervention interventionFromResultSet(ResultSet resultSet, Connection connection) {
        try {
            final String type = resultSet.getString("intervention_type");
            final String interventionName = resultSet.getString("intervention_name");
            final int interventionId = resultSet.getInt("intervention_id");
            switch (type) {
                case "command":
                    return new Command(
                            interventionId,
                            interventionName,
                            resultSet.getString("command"));
                case "question":
                    final int questionId = resultSet.getInt("question_id");

                    return new Question(
                            interventionId,
                            interventionName,
                            resultSet.getString("question"),
                            getAnswersByQuestionId(questionId, connection));
                case "questionnaire":
                    return new Questionnaire(
                            interventionId,
                            interventionName,
                            getQuestionsByQuestionnaireId(interventionId, connection));
                default:
                    return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "interventionFromResultSet::A database error occurred!", e);
        }
        return null;
    }

    @Override
    public List<Intervention> getInterventionsByLocationId(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getInterventionsByLocationId(?)")) {
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

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateCommand::A database error occurred!", e);
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
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL updateQuestion(?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, question);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    setAnswersForQuestion(resultSet.getInt("question_id"), answers, connection);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateQuestion::A database error occurred!", e);
        }
    }

    public void createQuestion(String name, String question, List<Answer> answers) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createQuestion(?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, question);

            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    setAnswersForQuestion(resultSet.getInt("question_id"), answers, connection);
                }
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
            LOGGER.log(Level.SEVERE, "getInterventions::A database error occurred!", e);
        }

        return new ArrayList<>();
    }

    @Override
    public void updateQuestionnaire(Questionnaire questionnaire) {
        final int id = questionnaire.getId();

        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL updateQuestionnaire(?, ?)")) {

            statement.setInt(1, id);
            statement.setString(2, questionnaire.getName());

            statement.executeUpdate();

            setQuestionsForQuestionnaire(id, questionnaire.getQuestions(), connection);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateQuestionnaire::A database error occurred!", e);
        }
    }

    @Override
    public void createQuestionnaire(String name, List<Question> questions) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createQuestionnaire(?)")) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    int questionnaireId = resultSet.getInt("intervention_id");

                    setQuestionsForQuestionnaire(questionnaireId, questions, connection);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "createQuestionnaire::A database error occurred!", e);
        }
    }

    @Override
    public void deleteIntervention(int id) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteIntervention(?)")) {

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "deleteIntervention::A database error occurred!", e);
        }
    }

    @Override
    public Intervention getInterventionById(int id) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getInterventionById(?)")) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return interventionFromResultSet(resultSet, connection);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getInterventionById::A database error occurred!", e);
        }

        return null;
    }
}
