package nl.han.aim.oosevt.lamport.data.entity;

public class Location {

    final private int id;
    final private String name;
    final private int delay;
    final private double longitude;
    final private double latitude;
    final private int radius;
    final private int areaId;

    public Location(int id, String name, int delay, double longitude, double latitude, int radius, int areaId) {
        this.id = id;
        this.name = name;
        this.delay = delay;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.areaId = areaId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDelay() {
        return delay;
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

    public int getAreaId() {
        return areaId;
    }
}
