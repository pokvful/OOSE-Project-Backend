package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MaxValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MinValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

import java.util.List;

public abstract class LocationRequestDTO extends RequestDTO {
    @NotEmpty
    @TranslatedName(name = "Naam")
    private String name;

    @NotEmpty
    @TranslatedName(name = "Delay")
    private int delay;

    @NotEmpty
    @MinValue(value = -180)
    @MaxValue(value = 180)
    @TranslatedName(name = "Lengtegraad")
    private double longitude;

    @NotEmpty
    @MinValue(value = -90)
    @MaxValue(value = 90)
    @TranslatedName(name = "Breedtegraad")
    private double latitude;

    @MinValue(value = 0)
    @NotEmpty
    @TranslatedName(name = "Straal")
    private int radius;

    @NotEmpty
    @TranslatedName(name = "Gebied")
    private int areaId;

    @TranslatedName(name = "Franchise")
    private int franchiseId;

    private List<Integer> linkedInterventions;

    public LocationRequestDTO() {
    }

    public LocationRequestDTO(String name, int delay, double longitude, double latitude, int radius, int areaId, int franchiseId, List<Integer> linkedInterventions) {
        this.name = name;
        this.delay = delay;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.areaId = areaId;
        this.franchiseId = franchiseId;
        this.linkedInterventions = linkedInterventions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public List<Integer> getLinkedInterventions() {
        return linkedInterventions;
    }

    public void setLinkedInterventions(List<Integer> linkedInterventions) {
        this.linkedInterventions = linkedInterventions;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }
}
