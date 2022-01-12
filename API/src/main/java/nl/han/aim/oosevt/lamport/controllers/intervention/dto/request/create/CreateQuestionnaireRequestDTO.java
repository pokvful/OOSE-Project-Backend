package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionnaireRequestDTO;

import java.util.List;

public class CreateQuestionnaireRequestDTO extends QuestionnaireRequestDTO {
    public CreateQuestionnaireRequestDTO(String name, List<QuestionRequestDTO> questions) {
        super(name, questions);
    }

    public CreateQuestionnaireRequestDTO() {}
}
