package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;

public class FranchiseRequestDTO extends RequestDTO {
    @NotEmpty
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
}
