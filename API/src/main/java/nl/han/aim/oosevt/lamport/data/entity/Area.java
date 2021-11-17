package nl.han.aim.oosevt.lamport.data.entity;

public class Area {

    private final int id;
    private final String name;
    private final double latitude;
    private final double longitude;
    private final int radius;

    public Area(int id, String name, double latitude, double longitude, int radius) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getRadius() {
        return radius;
    }
}
