package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Location;

public class LocationResponseDTO extends GeoFenceResponseDTO {
    private int locationId;
    private String name;

    public LocationResponseDTO() {
    }

    public LocationResponseDTO(int locationId, String name, double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.locationId = locationId;
        this.name = name;
    }

    public int getId() {
        return locationId;
    }

    public void setId(int id) {
        this.locationId = locationId;
    }

    public LocationResponseDTO fromData(Location location) {
        //todo implement
        //Bart - 15/11/2021
        return this;
    }
}

