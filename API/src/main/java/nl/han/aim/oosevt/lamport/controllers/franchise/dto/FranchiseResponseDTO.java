package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

import nl.han.aim.oosevt.lamport.data.entity.Franchise;

public class FranchiseResponseDTO {
    private final int id;
    private final String name;

    public FranchiseResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static FranchiseResponseDTO fromData(Franchise franchise) {
        return new FranchiseResponseDTO(
                franchise.getFranchiseId(),
                franchise.getFranchiseName()
        );
    }
}
