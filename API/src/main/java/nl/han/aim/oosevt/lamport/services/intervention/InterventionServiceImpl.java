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
}
