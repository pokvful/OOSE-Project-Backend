package nl.han.aim.oosevt.lamport.controllers.shared.dto;

public abstract class GeoFenceResponseDTO {
    private double longitude;
    private double latitude;
    private int radius;

    public GeoFenceResponseDTO(double longitude, double latitude, int radius) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }

    public GeoFenceResponseDTO() {
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
