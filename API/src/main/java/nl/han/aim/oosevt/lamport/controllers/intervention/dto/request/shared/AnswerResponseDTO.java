package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

public class AnswerResponseDTO {
    private String answer;
    private int id;

    public AnswerResponseDTO() {
    }

    public AnswerResponseDTO(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected void validateDTO() {

    }
}
