package nl.han.aim.oosevt.lamport.data.entity;

public class Area extends GeoFence {

    private final int id;
    private final String name;

    public Area(int id, String name, double longitude, double latitude, int radius) {
        super(longitude, latitude, radius);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
