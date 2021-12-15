package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

public class AnswerRequestDTO {
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
}
