package nl.han.aim.oosevt.lamport.controllers.intervention.dto.response;

import nl.han.aim.oosevt.lamport.data.entity.Answer;

public class AnswerResponseDTO {
    private final String answer;
    private final int id;

    public AnswerResponseDTO(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public static AnswerResponseDTO fromData(Answer answer) {
        return new AnswerResponseDTO(answer.getId(), answer.getAnswerText());
    }
}
