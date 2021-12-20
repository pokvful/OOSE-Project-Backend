package nl.han.aim.oosevt.lamport.services.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.InterventionResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.entity.Answer;
import nl.han.aim.oosevt.lamport.data.entity.Intervention;
import nl.han.aim.oosevt.lamport.data.entity.Question;
import nl.han.aim.oosevt.lamport.data.entity.Questionnaire;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class InterventionServiceImpl implements InterventionService {
    private final InterventionDAO interventionDAO;

    @Autowired
    public InterventionServiceImpl(InterventionDAO interventionDAO) {
        this.interventionDAO = interventionDAO;
    }

    private void assertInterventionExists(int id) {
        if (Objects.isNull(interventionDAO.getInterventionById(id))) {
            throw new NotFoundException();
        }
    }

    private List<Answer> answersFromCreateQuestionRequestDTO(List<AnswerRequestDTO> answerDTOS) {
        List<Answer> answers = new ArrayList<>();

        for (AnswerRequestDTO answerDTO : answerDTOS) {
            int answerId = answerDTO.getId();
            String answerText = answerDTO.getAnswer();

            Answer answer = new Answer(answerId, answerText);

            answers.add(answer);
        }

        return answers;
    }

    private List<Question> questionsFromCreateQuestionnaireRequestDTO(List<QuestionRequestDTO> questionDTOS) {
        List<Question> questions = new ArrayList<>();

        for (QuestionRequestDTO questionDTO : questionDTOS) {
            int questionId = 0;
            String questionName = questionDTO.getName();
            String questionText = questionDTO.getQuestion();
            List<AnswerRequestDTO> answerDTOS = questionDTO.getAnswers();

            List<Answer> answers = answersFromCreateQuestionRequestDTO(answerDTOS);

            Question question = new Question(questionId, questionName, questionText, answers);

            questions.add(question);
        }

        return questions;
    }

    @Override
    public void updateCommand(UpdateCommandRequestDTO updateCommandRequestDTO) {
        updateCommandRequestDTO.validate();

        final int id = updateCommandRequestDTO.getId();
        final String name = updateCommandRequestDTO.getName();
        final String command = updateCommandRequestDTO.getCommand();

        assertInterventionExists(id);

        interventionDAO.updateCommand(id, name, command);
    }

    @Override
    public void createCommand(CreateCommandRequestDTO createCommandRequestDTO) {
        createCommandRequestDTO.validate();
        interventionDAO.createCommand(createCommandRequestDTO.getName(), createCommandRequestDTO.getCommand());
    }

    @Override
    public void updateQuestion(UpdateQuestionRequestDTO updateQuestionRequestDTO) {
        updateQuestionRequestDTO.validate();

        assertInterventionExists(updateQuestionRequestDTO.getId());

        interventionDAO.updateQuestion(
                updateQuestionRequestDTO.getId(),
                updateQuestionRequestDTO.getName(),
                updateQuestionRequestDTO.getQuestion(),
                updateQuestionRequestDTO
                        .getAnswers()
                        .stream()
                        .map(x -> new Answer(x.getId(), x.getAnswer()))
                        .collect(Collectors.toList()));
    }

    public void createQuestion(CreateQuestionRequestDTO createQuestionRequestDTO) {
        createQuestionRequestDTO.validate();
        interventionDAO.createQuestion(
                createQuestionRequestDTO.getName(),
                createQuestionRequestDTO.getQuestion(),
                createQuestionRequestDTO
                        .getAnswers()
                        .stream()
                        .map(x -> new Answer(x.getId(), x.getAnswer()))
                        .collect(Collectors.toList()));
    }

    @Override
    public void updateQuestionnaire(UpdateQuestionnaireRequestDTO updateQuestionnaireRequestDTO) {
        updateQuestionnaireRequestDTO.validate();

        assertInterventionExists(updateQuestionnaireRequestDTO.getId());

        interventionDAO.updateQuestionnaire(new Questionnaire(
                updateQuestionnaireRequestDTO.getId(),
                updateQuestionnaireRequestDTO.getName(),
                updateQuestionnaireRequestDTO
                        .getQuestions()
                        .stream()
                        .map(questionDTO -> new Question(0,
                            questionDTO.getName(),
                            questionDTO.getQuestion(),
                            questionDTO
                                    .getAnswers()
                                    .stream()
                                    .map(answer -> new Answer(
                                            answer.getId(),
                                            answer.getAnswer()
                                    ))
                                    .collect(Collectors.toList())
                            ))
                        .collect(Collectors.toList())
        ));
    }

    @Override
    public void createQuestionnaire(CreateQuestionnaireRequestDTO createQuestionnaireRequestDTO) {
        createQuestionnaireRequestDTO.validate();

        String name = createQuestionnaireRequestDTO.getName();

        List<QuestionRequestDTO> questionDTOS = createQuestionnaireRequestDTO.getQuestions();


        List<Question> questions = questionsFromCreateQuestionnaireRequestDTO(questionDTOS);

        interventionDAO.createQuestionnaire(name, questions);
    }

    @Override
    public List<InterventionResponseDTO> getInterventions() {
        return interventionDAO
                .getInterventions()
                .stream()
                .map(InterventionResponseDTO::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public InterventionResponseDTO getInterventionById(int id) {
        Intervention intervention = interventionDAO.getInterventionById(id);

        if (intervention == null) {
            throw new NotFoundException();
        }

        InterventionResponseDTO interventionResponseDTO = InterventionResponseDTO.fromData(intervention);

        return interventionResponseDTO;
    }

    @Override
    public void deleteIntervention(int id) {
        assertInterventionExists(id);

        interventionDAO.deleteIntervention(id);
    }
}
