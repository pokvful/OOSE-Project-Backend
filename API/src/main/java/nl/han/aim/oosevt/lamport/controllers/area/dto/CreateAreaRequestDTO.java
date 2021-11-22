package nl.han.aim.oosevt.lamport.controllers.area.dto;

public class CreateAreaRequestDTO extends AreaRequestDTO {

    public CreateAreaRequestDTO() {
    }

    public CreateAreaRequestDTO(String name, double longitude, double latitude, int radius) {
        super(name, longitude, latitude, radius);
    }
}
