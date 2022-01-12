package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;

public class ProfileQuestionRequestDTO extends RequestDTO {
    @NotEmpty
    protected String name;

    public ProfileQuestionRequestDTO(String name) {
        this.name = name;
    }

    public ProfileQuestionRequestDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }
}
