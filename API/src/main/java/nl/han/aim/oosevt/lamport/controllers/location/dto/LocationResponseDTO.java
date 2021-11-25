package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.InterventionResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationResponseDTO extends GeoFenceResponseDTO {
    private int id;
    private String name;
    private int delay;

    private AreaResponseDTO area;
    private List<InterventionResponseDTO> linkedInterventions;

    public LocationResponseDTO() {
    }

    public LocationResponseDTO(int locationId, String name, double longitude, double latitude, int radius, AreaResponseDTO area, int delay, List<InterventionResponseDTO> linkedInterventions) {
        super(longitude, latitude, radius);
        this.id = locationId;
        this.name = name;
        this.area = area;
        this.delay = delay;
        this.linkedInterventions = linkedInterventions;
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
        this.id = location.getId();
        this.name = location.getName();
        this.delay = location.getDelay();

        this.area = new AreaResponseDTO().fromData(location.getArea());
        this.linkedInterventions = location
                .getLinkedInterventions()
                .stream()
                .map(intervention -> new InterventionResponseDTO().fromData(intervention))
                .collect(Collectors.toList());

        return this;
    }

    public List<InterventionResponseDTO> getLinkedInterventions() {
        return linkedInterventions;
    }

    public void setLinkedInterventions(List<InterventionResponseDTO> linkedInterventions) {
        this.linkedInterventions = linkedInterventions;
    }
}

