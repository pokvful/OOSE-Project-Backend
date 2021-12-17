package nl.han.aim.oosevt.lamport.controllers.intervention.dto.response;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;

import java.util.List;

public class QuestionnaireResponseDTO extends InterventionResponseDTO {
    private List<QuestionResponseDTO> questions;

    public QuestionnaireResponseDTO(int id, String name, List<QuestionResponseDTO> questions) {
        super(id, name);
        this.questions = questions;
    }

    public List<QuestionResponseDTO> getQuestions() {
        return questions;
    }

}
