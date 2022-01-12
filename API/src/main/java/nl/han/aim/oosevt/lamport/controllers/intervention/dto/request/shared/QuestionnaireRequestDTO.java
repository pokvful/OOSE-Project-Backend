package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import java.util.List;

public class QuestionnaireRequestDTO extends InterventionRequestDTO {
    protected List<QuestionRequestDTO> questions;

    public QuestionnaireRequestDTO() {}

    public QuestionnaireRequestDTO(String name, List<QuestionRequestDTO> questions) {
        super(name);
        this.questions = questions;
    }

    public List<QuestionRequestDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequestDTO> questions) { this.questions = questions; }
}
