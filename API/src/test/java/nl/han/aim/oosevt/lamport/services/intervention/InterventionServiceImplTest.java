package nl.han.aim.oosevt.lamport.services.intervention;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.*;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.entity.*;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private int answerIdA = 1;
    private int answerIdB = 2;
    private int answerIdC = 3;

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

    private final int commandId = 1;
    private final String commandName = "Name";
    private final String commandText = "Command";

    private Questionnaire mockQuestionnaire;

    private List<AnswerRequestDTO> answerRequestDTO;

    private CreateQuestionRequestDTO createQuestionRequestDTO;
    private UpdateQuestionRequestDTO updateQuestionRequestDTO;

    private UpdateQuestionnaireRequestDTO updateInterventionRequestC;
    private List<QuestionRequestDTO> questionRequestDTOs;
    private List<Intervention> interventions;
    private List<InterventionResponseDTO> interventionDTOS;
    private CreateQuestionnaireRequestDTO createQuestionnaireRequestDTO;
    private List<AnswerResponseDTO> answerResponseDTO;
    private CommandResponseDTO commandResponseDTO;

    private ArgumentCaptor<Object> objectCaptor;

    @BeforeEach
    public void setup() {
        mockInterventionDAO = Mockito.mock(InterventionDAO.class);
        objectCaptor = ArgumentCaptor.forClass(Object.class);

        answerA = new Answer(answerIdA, answerAAnswer);
        answerB = new Answer(answerIdB, answerBAnswer);
        answerC = new Answer(answerIdC, answerCAnswer);

        answers = new ArrayList<>();

        answers.add(answerA);
        answers.add(answerB);
        answers.add(answerC);

        commandResponseDTO = new CommandResponseDTO(interventionIdA, interventionNameA, commandA);

        answerRequestDTO = new ArrayList<>();

        answerRequestDTO.add(new AnswerRequestDTO(answerAAnswer));
        answerRequestDTO.add(new AnswerRequestDTO(answerBAnswer));
        answerRequestDTO.add(new AnswerRequestDTO(answerCAnswer));

        answerResponseDTO = new ArrayList<>();

        answerResponseDTO.add(new AnswerResponseDTO(answerIdA, answerAAnswer));
        answerResponseDTO.add(new AnswerResponseDTO(answerIdB, answerBAnswer));
        answerResponseDTO.add(new AnswerResponseDTO(answerIdC, answerCAnswer));

        questions = new ArrayList<>();

        interventionA = new Command(interventionIdA, interventionNameA, commandA);
        interventionB = new Question(interventionIdB, interventionNameB, interventionQuestionB, answers);

        questions.add(interventionB);

        interventionC = new Questionnaire(interventionIdC, interventionNameC, questions);

        mockQuestionnaire = new Questionnaire(interventionC.getId(), interventionC.getName(), interventionC.getQuestions().stream().map(q -> new Question(0, q.getName(), q.getQuestionText(), q.getAnswers().stream().map(a -> new Answer(0, a.getAnswerText())).collect(Collectors.toList()))).collect(Collectors.toList()));

        updateInterventionRequestA = new UpdateCommandRequestDTO(interventionNameA, commandA, interventionIdA);

        sut = new InterventionServiceImpl(mockInterventionDAO);

        createCommandRequestDTO = Mockito.spy(new CreateCommandRequestDTO(commandName, commandText));

        updateQuestionRequestDTO = Mockito.spy(new UpdateQuestionRequestDTO(interventionIdB, interventionNameB, interventionQuestionB, answerRequestDTO));

        command = new Command(commandId, commandName, commandText);

        createQuestionRequestDTO = Mockito.spy(new CreateQuestionRequestDTO(interventionNameB, answerRequestDTO, interventionQuestionB));

        questionRequestDTOs = new ArrayList<>();
        questionRequestDTOs.add(createQuestionRequestDTO);

        updateInterventionRequestC = new UpdateQuestionnaireRequestDTO(interventionNameC, questionRequestDTOs, interventionIdC);

        createQuestionnaireRequestDTO = new CreateQuestionnaireRequestDTO(interventionNameC, questionRequestDTOs);

        interventions = new ArrayList<>();

        interventions.add(interventionA);

        interventionDTOS = new ArrayList<>();

        interventionDTOS.add(commandResponseDTO);
    }

    @Test
    public void getInterventions() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventions()).thenReturn(interventions);

        // Act
        List<InterventionResponseDTO> response = sut.getInterventions();

        // Assert
        ObjectAssertions.assertEquals(interventionDTOS, response);
    }

    @Test
    public void getInterventionById() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdA)).thenReturn(interventionA);

        // Act
        InterventionResponseDTO response = sut.getInterventionById(interventionIdA);

        // Assert
        ObjectAssertions.assertEquals(commandResponseDTO, response);
    }

    @Test
    public void getInterventionByIdNonExistent() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdC)).thenReturn(null);

        // Act
        Executable act = () -> sut.getInterventionById(interventionIdC);

        // Assert
        Assertions.assertThrows(NotFoundException.class, act);
    }

    @Test
    public void updateQuestionnaireCallsDB() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdC)).thenReturn(interventionC);

        // Act
        sut.updateQuestionnaire(updateInterventionRequestC);

        // Assert
        Mockito.verify(mockInterventionDAO).updateQuestionnaire((Questionnaire) objectCaptor.capture());
        ObjectAssertions.assertEquals(mockQuestionnaire, objectCaptor.getValue());
    }

    @Test
    public void updateQuestionnaireNonExistent() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdC)).thenReturn(null);

        // Act
        Executable act = () -> sut.updateQuestionnaire(updateInterventionRequestC);

        // Assert
        Assertions.assertThrows(NotFoundException.class, act);
    }

    @Test
    public void createQuestionnaireCallsDB() {
        // Act
        sut.createQuestionnaire(createQuestionnaireRequestDTO);

        // Assert
        Mockito.verify(mockInterventionDAO).createQuestionnaire(ArgumentMatchers.eq(interventionNameC), (List<Question>) objectCaptor.capture());
        ObjectAssertions.assertEquals(mockQuestionnaire.getQuestions(), objectCaptor.getValue());
    }

    @Test
    public void deleteInterventionCallsDB() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdC)).thenReturn(interventionC);

        // Act
        sut.deleteIntervention(interventionIdC);

        // Assert
        Mockito.verify(mockInterventionDAO).deleteIntervention(interventionIdC);
    }

    @Test
    public void deleteInterventionNonExistent() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdC)).thenReturn(null);

        // Act
        Executable act = () -> sut.deleteIntervention(interventionIdC);

        // Assert
        Assertions.assertThrows(NotFoundException.class, act);

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
        sut.createQuestion(new CreateQuestionRequestDTO(interventionNameB, answerRequestDTO, interventionQuestionB));

        // Assert
        Mockito.verify(mockInterventionDAO).createQuestion(ArgumentMatchers.eq(interventionNameB), ArgumentMatchers.eq(interventionQuestionB), ArgumentMatchers.any());
    }

    @Test
    public void updateQuestionHappy() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdB)).thenReturn(interventionB);

        // Act
        sut.updateQuestion(updateQuestionRequestDTO);

        // Assert
        Mockito.verify(mockInterventionDAO).updateQuestion(ArgumentMatchers.eq(interventionIdB), ArgumentMatchers.eq(interventionNameB), ArgumentMatchers.eq(interventionQuestionB), ArgumentMatchers.any());
    }

    @Test
    public void updateQuestionNonExistent() {
        // Arrange
        Mockito.when(mockInterventionDAO.getInterventionById(interventionIdB)).thenReturn(null);

        // Act
        Executable act = () -> sut.updateQuestion(updateQuestionRequestDTO);

        // Assert
        Assertions.assertThrows(NotFoundException.class, act);
    }
}
