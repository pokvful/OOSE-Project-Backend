package nl.han.aim.oosevt.lamport.data.dao.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.data.entity.Answer;
import nl.han.aim.oosevt.lamport.data.entity.Intervention;

import java.util.Arrays;
import java.util.List;

public interface InterventionDAO {
    List<Intervention> getInterventionsByLocationId(int locationId);

    void updateCommand(int id, String name, String command);

    Intervention getInterventionById(int id);

    void createCommand(String name, String command);

    void updateQuestion(int id, String name, String question, List<Answer> answers);

    void createQuestion(String name, String question, List<Answer> answers);

    List<Intervention> getInterventions();
}
