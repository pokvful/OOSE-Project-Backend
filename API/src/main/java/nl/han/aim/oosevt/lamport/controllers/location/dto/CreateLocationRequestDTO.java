package nl.han.aim.oosevt.lamport.controllers.location.dto;

public class CreateLocationRequestDTO extends LocationRequestDTO {

    public CreateLocationRequestDTO() {
    }

    public CreateLocationRequestDTO(String name, int delay, double longitude, double latitude, int radius, int areaId) {
        super(name, delay, longitude, latitude, radius, areaId);
    }

    @Override
    protected void validateSpecificDto() {

    }
}

