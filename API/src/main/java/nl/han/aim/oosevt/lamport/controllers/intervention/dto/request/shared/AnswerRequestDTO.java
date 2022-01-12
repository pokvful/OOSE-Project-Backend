package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

public class AnswerRequestDTO extends RequestDTO {
    private int id;
    @TranslatedName(name = "Antwoordtekst")
    private String answerText;

    public AnswerRequestDTO() {}

    public AnswerRequestDTO(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) { this.answerText = answerText; }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
