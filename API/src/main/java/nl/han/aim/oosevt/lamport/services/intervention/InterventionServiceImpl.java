package nl.han.aim.oosevt.lamport.services.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.entity.Answer;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        final AnswerRequestDTO answerRequestDTO = updateQuestionRequestDTO.getAnswer();
        final List<Answer> answer = new ArrayList<>();
        answer.add(answerRequestDTO.getAnswer());

        updateQuestionRequestDTO.validate();

        assertInterventionExists(updateQuestionRequestDTO.getId());

        interventionDAO.updateQuestion(updateQuestionRequestDTO.getId(), updateQuestionRequestDTO.getName(), updateQuestionRequestDTO.getQuestion(),
                answer);
    }

    public void createQuestion(CreateQuestionRequestDTO createQuestionRequestDTO) {
        final AnswerRequestDTO answerRequestDTO = createQuestionRequestDTO.getAnswer();
        final List<Answer> answer = new ArrayList<>();
        answer.add(answerRequestDTO.getAnswer());
        createQuestionRequestDTO.validate();
        interventionDAO.createQuestion(createQuestionRequestDTO.getName(), createQuestionRequestDTO.getQuestion(), answer);
    }
}
