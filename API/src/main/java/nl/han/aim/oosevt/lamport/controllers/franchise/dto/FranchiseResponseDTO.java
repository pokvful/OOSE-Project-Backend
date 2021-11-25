package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

import nl.han.aim.oosevt.lamport.data.entity.Franchise;

public class FranchiseResponseDTO {
    private int id;
    private String name;

    public FranchiseResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FranchiseResponseDTO() {
    }

    public FranchiseResponseDTO fromData(Franchise franchise) {
        this.id = franchise.getFranchiseId();
        this.name = franchise.getFranchiseName();

        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
