package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

public class FranchiseRequestDTO extends RequestDTO {
    @NotEmpty
    @TranslatedName(name = "Naam")
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
