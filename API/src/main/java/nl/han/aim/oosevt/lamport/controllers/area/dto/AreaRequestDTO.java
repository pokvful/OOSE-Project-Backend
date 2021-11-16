package nl.han.aim.oosevt.lamport.controllers.area.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public abstract class AreaRequestDTO extends RequestDTO {
    private String name;
    private double longitude;
    private double latitude;
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

    @Override
    protected void validateDTO() {
        if(name.isEmpty()) {
            addError("name", "Naam mag niet leeg zijn!");
        }
        if(latitude < -90 || latitude > 90) {
            addError("longitude", "Dit is geen geldige lengtegraad");
        }
        if(longitude == 0) {
            addError("longitude", "Lengtegraad mag niet leeg zijn!");
        }
        if(longitude < -180 || longitude > 180) {
            addError("longitude", "Dit is geen geldige breedtegraad");
        }
        if(latitude == 0) {
            addError("latitude", "Breedtegraad mag niet leeg zijn!");
        }
        if(radius <= 0) {
            addError("radius", "Straal mag niet kleiner zijn dan 0");
        }
        validateSpecificDto();
    }

    protected abstract void validateSpecificDto();
}
