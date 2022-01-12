package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceRequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

import java.util.List;

public abstract class LocationRequestDTO extends GeoFenceRequestDTO {
    @NotEmpty
    @TranslatedName(name = "Naam")
    private String name;

    @NotEmpty
    @TranslatedName(name = "Delay")
    private int delay;

    @NotEmpty
    @TranslatedName(name = "Gebied")
    private int areaId;

    @TranslatedName(name = "Franchise")
    private int franchiseId;

    private List<Integer> linkedInterventions;

    public LocationRequestDTO() {}

    public LocationRequestDTO(String name, int delay, double longitude, double latitude, int radius, int areaId, int franchiseId, List<Integer> linkedInterventions) {
        super(longitude, latitude, radius);
        this.name = name;
        this.delay = delay;
        this.areaId = areaId;
        this.franchiseId = franchiseId;
        this.linkedInterventions = linkedInterventions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) { this.delay = delay; }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) { this.areaId = areaId; }

    public List<Integer> getLinkedInterventions() {
        return linkedInterventions;
    }

    public void setLinkedInterventions(List<Integer> linkedInterventions) { this.linkedInterventions = linkedInterventions; }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) { this.franchiseId = franchiseId; }
}
