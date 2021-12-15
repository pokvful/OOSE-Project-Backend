package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;

public class CreateQuestionRequestDTO extends QuestionRequestDTO {

    @Override
    protected void validateDTO() {

    }

    public CreateQuestionRequestDTO(String name, AnswerRequestDTO answer, String question) {
        super(name, answer, question);

    }

    public CreateQuestionRequestDTO() {

    }

}
