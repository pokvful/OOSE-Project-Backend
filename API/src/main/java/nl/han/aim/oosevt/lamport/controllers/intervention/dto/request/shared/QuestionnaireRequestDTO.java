package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import java.util.List;

public abstract class QuestionnaireRequestDTO extends InterventionRequestDTO {
    private List<QuestionRequestDTO> questions;

    public QuestionnaireRequestDTO(String name, List<QuestionRequestDTO> questions) {
        super(name);
        this.questions = questions;
    }

    public List<QuestionRequestDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequestDTO> questions) {
        this.questions = questions;
    }
}
