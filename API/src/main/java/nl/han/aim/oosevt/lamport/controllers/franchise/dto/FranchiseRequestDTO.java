package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public class FranchiseRequestDTO extends RequestDTO {
    private String name;

    public FranchiseRequestDTO(String name) {
        this.name = name;
    }

    public FranchiseRequestDTO() {
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
    }
}
