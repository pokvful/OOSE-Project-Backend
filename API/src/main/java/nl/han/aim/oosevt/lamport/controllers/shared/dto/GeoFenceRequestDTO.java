package nl.han.aim.oosevt.lamport.controllers.shared.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MaxValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MinValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

public class GeoFenceRequestDTO extends RequestDTO {
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

    public GeoFenceRequestDTO(double longitude, double latitude, int radius) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }

    public GeoFenceRequestDTO() {}

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) { this.radius = radius; }
}
