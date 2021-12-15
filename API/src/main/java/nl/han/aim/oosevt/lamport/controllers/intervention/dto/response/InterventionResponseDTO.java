package nl.han.aim.oosevt.lamport.controllers.intervention.dto.response;

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

    public static InterventionResponseDTO fromData(Intervention intervention) {
        return new InterventionResponseDTO(
                intervention.getId(),
                intervention.getName()
        );
    }
}
