package nl.han.aim.oosevt.lamport.services.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.entity.Answer;
import nl.han.aim.oosevt.lamport.data.entity.Command;
import nl.han.aim.oosevt.lamport.data.entity.Question;
import nl.han.aim.oosevt.lamport.data.entity.Questionnaire;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class InterventionServiceImplTest {
    private final int interventionIdA = 1;
    private final String interventionNameA = "saladebar";
    private final String commandA = "ga naar de saladebar";

    private final int interventionIdB = 2;
    private final String interventionNameB = "bijbelvertaler";
    private final String interventionQuestionB = "Wie is een bijbelvertaler";

    private final int interventionIdC = 3;
    private final String interventionNameC = "kerken tellen";

    private final String answerAAnswer = "Maarten Luther";
    private final String answerBAnswer = "Willibrord";
    private final String answerCAnswer = "Calvijn";

    private Answer answerA;
    private Answer answerB;
    private Answer answerC;

    private Command interventionA;
    private Question interventionB;
    private Questionnaire interventionC;

    private List<Answer> answers;
    private List<Question> questions;

    private UpdateCommandRequestDTO updateInterventionRequestA;

    private InterventionDAO mockInterventionDAO;

    private InterventionService sut;

    private CreateCommandRequestDTO createCommandRequestDTO;
    private Command command;

    final int commandId = 1;
    final String commandName = "Name";
    final String commandText = "Command";

    final int questionId = 2;
    final String questionName = "questionName";
    final String questionText = "questionText";

    List<AnswerRequestDTO> answerRequestDTO;

    CreateQuestionRequestDTO createQuestionRequestDTO;
    UpdateQuestionRequestDTO updateQuestionRequestDTO;

    @BeforeEach
    public void setup() {
        mockInterventionDAO = Mockito.mock(InterventionDAO.class);

        answerA = new Answer(1, answerAAnswer);
        answerB = new Answer(2, answerBAnswer);
        answerC = new Answer(3, answerCAnswer);

        answers = new ArrayList<>();

        answers.add(answerA);
        answers.add(answerB);
        answers.add(answerC);

        questions = new ArrayList<>();

        interventionA = new Command(interventionIdA, interventionNameA, commandA);
        interventionB = new Question(interventionIdB, interventionNameB, interventionQuestionB, answers);

        questions.add(interventionB);

        interventionC = new Questionnaire(interventionIdC, interventionNameC, questions);

        updateInterventionRequestA = new UpdateCommandRequestDTO(interventionNameA, commandA, interventionIdA);

        sut = new InterventionServiceImpl(mockInterventionDAO);

        createCommandRequestDTO = Mockito.spy(new CreateCommandRequestDTO(commandName, commandText));

        answerRequestDTO = new ArrayList<>();

        answerRequestDTO.add(new AnswerRequestDTO("antwoord"));

        updateQuestionRequestDTO = Mockito.spy(new UpdateQuestionRequestDTO(questionId, questionName, questionText, answerRequestDTO));

        command = new Command(commandId, commandName, commandText);

        createQuestionRequestDTO = Mockito.spy(new CreateQuestionRequestDTO(questionName, answerRequestDTO, questionText));
    }

    @Test
    public void updateCommandHappy() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdA)).thenReturn(interventionA);

        // Act
        sut.updateCommand(updateInterventionRequestA);

        // Assert
        Mockito.verify(mockInterventionDAO).updateCommand(interventionIdA, interventionNameA, commandA);
    }

    @Test
    public void updateCommandNonExistent() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdA)).thenReturn(null);

        // Act
        Executable act = () -> sut.updateCommand(updateInterventionRequestA);

        // Assert
        Assertions.assertThrows(NotFoundException.class, act);
    }

    @Test
    public void testCreateCommandValidates() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(commandId)).thenReturn(command);

        // Act
        sut.createCommand(createCommandRequestDTO);

        // Assert
        Mockito.verify(createCommandRequestDTO).validate();
    }

    @Test
    public void testCreateCommandCallsDB() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdB)).thenReturn(interventionB);

        // Act
        sut.createCommand(new CreateCommandRequestDTO(commandName, commandText));

        // Assert
        Mockito.verify(mockInterventionDAO).createCommand(commandName, commandText);
    }

    @Test
    public void testCreateQuestionValidates() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(commandId)).thenReturn(command);

        // Act
        sut.createQuestion(createQuestionRequestDTO);

        // Assert
        Mockito.verify(createQuestionRequestDTO).validate();
    }

    @Test
    public void testCreateQuestionCallsDB() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdB)).thenReturn(interventionB);

        // Act
        sut.createQuestion(new CreateQuestionRequestDTO(questionName, answerRequestDTO, questionText));

        // Assert
        Mockito.verify(mockInterventionDAO).createQuestion(ArgumentMatchers.eq(questionName), ArgumentMatchers.eq(questionText), ArgumentMatchers.any());
    }

    @Test
    public void updateQuestionHappy() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdB)).thenReturn(interventionB);

        // Act
        sut.updateQuestion(updateQuestionRequestDTO);

        // Assert
        Mockito.verify(mockInterventionDAO).updateQuestion(ArgumentMatchers.eq(questionId), ArgumentMatchers.eq(questionName), ArgumentMatchers.eq(questionText), ArgumentMatchers.any());
    }

    @Test
    public void updateQuestionNonExistent() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(questionId)).thenReturn(null);

        // Act
        Executable act = () -> sut.updateQuestion(updateQuestionRequestDTO);

        // Assert
        Assertions.assertThrows(NotFoundException.class, act);
    }
}
