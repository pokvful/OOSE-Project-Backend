package nl.han.aim.oosevt.lamport.controllers.intervention.dto;

public class InterventionResponseDTO {
    private int id;
    private String name;

    public InterventionResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
