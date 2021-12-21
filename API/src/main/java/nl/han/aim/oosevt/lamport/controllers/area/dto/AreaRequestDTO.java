package nl.han.aim.oosevt.lamport.controllers.area.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MaxValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MinValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;

public abstract class AreaRequestDTO extends RequestDTO {
    @NotEmpty
    private String name;

    @NotEmpty
    @MinValue(value = -180)
    @MaxValue(value = 180)
    private double longitude;

    @NotEmpty
    @MinValue(value = -90)
    @MaxValue(value = 90)
    private double latitude;

    @MinValue(value = 0)
    @NotEmpty
    private int radius;

    public AreaRequestDTO() {
    }

    public AreaRequestDTO(String name, double longitude, double latitude, int radius) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
