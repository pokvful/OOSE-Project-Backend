package nl.han.aim.oosevt.lamport.controllers.area.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Area;

public class AreaResponseDTO extends GeoFenceResponseDTO {
    private int id;

    public AreaResponseDTO() {
    }

    public AreaResponseDTO(int id, double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AreaResponseDTO fromData(Area area) {
        //todo implement
        //Bart - 15/11/2021
        return this;
    }
}
