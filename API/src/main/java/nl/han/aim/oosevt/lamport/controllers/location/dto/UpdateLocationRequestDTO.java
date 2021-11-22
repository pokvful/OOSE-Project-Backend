package nl.han.aim.oosevt.lamport.controllers.location.dto;

import java.util.List;

public class UpdateLocationRequestDTO extends LocationRequestDTO {
    private int locationId;

    public UpdateLocationRequestDTO() {
    }

    public UpdateLocationRequestDTO(int locationId, String name, int delay, double longitude, double latitude, int radius, int areaId, List<Integer> linkedInterventions) {
        super(name, delay, longitude, latitude, radius, areaId, linkedInterventions);
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    protected void validateSpecificDto() {
        if(locationId == 0) {
            addError("id", "Id mag niet leeg zijn");
        }
    }
}
