package nl.han.aim.oosevt.lamport.controllers.area.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceRequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

public abstract class AreaRequestDTO extends GeoFenceRequestDTO {
    @NotEmpty
    @TranslatedName(name = "Naam")
    private String name;

    public AreaRequestDTO() {}

    public AreaRequestDTO(String name, double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }
}
