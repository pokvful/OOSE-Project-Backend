package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;

import java.util.List;

public class CreateQuestionRequestDTO extends QuestionRequestDTO {

    @Override
    protected void validateDTO() {

    }

    public CreateQuestionRequestDTO(String name, List<AnswerRequestDTO> answers, String question) {
        super(name, answers, question);

    }

    public CreateQuestionRequestDTO() {

    }

}
