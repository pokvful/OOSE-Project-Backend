package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public class GoalRequestDTO extends RequestDTO {
    private String name;

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

    @Override
    protected void validateDTO() {
        if(name.isEmpty()) {
            addError("name", "Naam kan niet leeg zijn");
        }
        validateSpecificDTO();
    }

    public void validateSpecificDTO() {}
}
