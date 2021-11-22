package nl.han.aim.oosevt.lamport.controllers.location.dto;

import java.util.List;

public class CreateLocationRequestDTO extends LocationRequestDTO {

    public CreateLocationRequestDTO() {
    }

    public CreateLocationRequestDTO(String name, int delay, double longitude, double latitude, int radius, int areaId, List<Integer> linkedInterventions) {
        super(name, delay, longitude, latitude, radius, areaId, linkedInterventions);
    }
}

