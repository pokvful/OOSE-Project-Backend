package nl.han.aim.oosevt.lamport.controllers.location.dto;

public class UpdateLocationRequestDTO extends LocationRequestDTO {
    private int locationId;

    public UpdateLocationRequestDTO() {
    }

    public UpdateLocationRequestDTO(int locationId, String name, int delay, double longitude, double latitude, int radius, int areaId) {
        super(name, delay, longitude, latitude, radius, areaId);
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
