package nl.han.aim.oosevt.lamport.data.entity;

public class Area {

    public final int id;
    public final String name;
    public final double latitude;
    public final double longitude;
    public final int radius;

    public Area(int id, String name, double latitude, double longitude, int radius) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

}
