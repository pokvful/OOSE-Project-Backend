package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public abstract class InterventionRequestDTO extends RequestDTO {
    protected String name;

    public InterventionRequestDTO() {}

    public InterventionRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
