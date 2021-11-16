package nl.han.aim.oosevt.lamport.controllers.area.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Area;

public class AreaResponseDTO extends GeoFenceResponseDTO {
    private int id;
    private String name;

    public AreaResponseDTO() {
    }

    public AreaResponseDTO(int id, String name, double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaResponseDTO fromData(Area area) {
        //todo implement
        //Bart - 15/11/2021

        //todo generics
        //Wesley - 16/11/2021
        return this;
    }
}
