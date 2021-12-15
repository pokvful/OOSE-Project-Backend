package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public class AnswerRequestDTO extends RequestDTO {
    private String answer;

    public AnswerRequestDTO() {
    }

    public AnswerRequestDTO(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    protected void validateDTO() {

    }
}
