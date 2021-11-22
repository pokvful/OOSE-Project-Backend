package nl.han.aim.oosevt.lamport.controllers.intervention.dto;

import nl.han.aim.oosevt.lamport.data.entity.Intervention;

public class InterventionResponseDTO {
    private int id;
    private String name;

    public InterventionResponseDTO() {}

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

    public InterventionResponseDTO fromData(Intervention intervention) {
        this.id = intervention.getId();
        this.name = intervention.getName();

        return this;
    }
}
