package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Location;

import java.util.List;

public class LocationResponseDTO extends GeoFenceResponseDTO {
    private int locationId;
    private String name;
    private List<Integer> linkedInterventions;

    public LocationResponseDTO() {
    }

    public LocationResponseDTO(int locationId, String name, double longitude, double latitude, int radius, List<Integer> linkedInterventions) {
        super(longitude, latitude, radius);
        this.locationId = locationId;
        this.name = name;
        this.linkedInterventions = linkedInterventions;
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

    public LocationResponseDTO fromData(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.radius = location.getRadius();
        this.locationId = location.getId();
        this.name = location.getName();
        this.linkedInterventions = location.getLinkedInterventions();
    
        return this;
    }

    public List<Integer> getLinkedInterventions() {
        return linkedInterventions;
    }

    public void setLinkedInterventions(List<Integer> linkedInterventions) {
        this.linkedInterventions = linkedInterventions;
    }
}

