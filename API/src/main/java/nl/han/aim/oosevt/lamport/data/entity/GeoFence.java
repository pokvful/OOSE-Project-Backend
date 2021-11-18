package nl.han.aim.oosevt.lamport.data.entity;

public class GeoFence {
    private final double longitude;
    private final double latitude;
    private final int radius;

    public GeoFence(double longitude, double latitude, int radius) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getRadius() {
        return radius;
    }
}
