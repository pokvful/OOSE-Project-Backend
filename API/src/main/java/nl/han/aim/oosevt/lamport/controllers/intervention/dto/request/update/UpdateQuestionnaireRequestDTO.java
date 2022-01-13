package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionnaireRequestDTO;

import java.util.List;

public class UpdateQuestionnaireRequestDTO extends QuestionnaireRequestDTO {
    private int id;

    public UpdateQuestionnaireRequestDTO(String name, List<QuestionRequestDTO> questions, int id) {
        super(name, questions);
        this.id = id;
    }

    public UpdateQuestionnaireRequestDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
