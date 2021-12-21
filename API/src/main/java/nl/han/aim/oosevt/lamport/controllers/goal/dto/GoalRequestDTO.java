package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;

public class GoalRequestDTO extends RequestDTO {
    @NotEmpty
    protected String name;

    public GoalRequestDTO(String name) {
        this.name = name;
    }

    public GoalRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
