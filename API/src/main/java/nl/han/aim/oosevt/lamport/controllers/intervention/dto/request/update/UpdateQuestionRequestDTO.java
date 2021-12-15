package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;

public class UpdateQuestionRequestDTO extends QuestionRequestDTO {
    private int id;

    @Override
    protected void validateDTO() {

    }

    public UpdateQuestionRequestDTO(int id, String name, String question, AnswerRequestDTO answer) {
        super(name, answer, question);
        this.id = id;
    }

    public UpdateQuestionRequestDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
