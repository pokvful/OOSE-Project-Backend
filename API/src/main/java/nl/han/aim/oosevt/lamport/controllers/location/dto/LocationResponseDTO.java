package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Location;

public class LocationResponseDTO extends GeoFenceResponseDTO {
    private int id;

    public LocationResponseDTO() {
    }

    public LocationResponseDTO(int id, double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocationResponseDTO fromData(Location location) {
        //todo implement
        //Bart - 15/11/2021
        return this;
    }
}

