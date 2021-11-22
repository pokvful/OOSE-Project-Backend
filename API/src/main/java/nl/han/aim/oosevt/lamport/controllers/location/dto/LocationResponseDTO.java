package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Location;

public class LocationResponseDTO extends GeoFenceResponseDTO {
    private int locationId;
    private String name;
    private int delay;

    private AreaResponseDTO area;

    public LocationResponseDTO() {
    }

    public LocationResponseDTO(int locationId, String name, double longitude, double latitude, int radius, AreaResponseDTO area, int delay) {
        super(longitude, latitude, radius);
        this.locationId = locationId;
        this.name = name;
        this.area = area;
        this.delay = delay;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaResponseDTO getArea() {
        return area;
    }

    public void setArea(AreaResponseDTO area) {
        this.area = area;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public LocationResponseDTO fromData(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.radius = location.getRadius();
        this.locationId = location.getId();
        this.name = location.getName();
        this.delay = location.getDelay();

        this.area = new AreaResponseDTO().fromData(location.getArea());
    
        return this;
    }
}

