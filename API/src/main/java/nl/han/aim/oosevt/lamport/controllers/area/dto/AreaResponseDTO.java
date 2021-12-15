package nl.han.aim.oosevt.lamport.controllers.area.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Area;

public class AreaResponseDTO extends GeoFenceResponseDTO {
    private final int id;
    private final String name;

    public AreaResponseDTO(int id, String name, double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AreaResponseDTO fromData(Area area) {
        return new AreaResponseDTO(
                area.getId(),
                area.getName(),
                area.getLatitude(),
                area.getLongitude(),
                area.getRadius());
    }
}
