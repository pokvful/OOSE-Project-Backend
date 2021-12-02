package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public class UpdateGoalRequestDTO extends RequestDTO {
    private String name;
    private int id;

    public UpdateGoalRequestDTO(String name) {
        this.name = name;
    }

    public UpdateGoalRequestDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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