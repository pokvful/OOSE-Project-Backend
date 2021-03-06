package nl.han.aim.oosevt.lamport.controllers.location.dto;

import java.util.List;

public class UpdateLocationRequestDTO extends LocationRequestDTO {
    private int id;

    public UpdateLocationRequestDTO() {}

    public UpdateLocationRequestDTO(int id, String name, int delay, double longitude, double latitude, int radius, int areaId, int franchiseId, List<Integer> linkedInterventions) {
        super(name, delay, longitude, latitude, radius, areaId, franchiseId, linkedInterventions);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
