package nl.han.aim.oosevt.lamport.data.entity;

public class Location extends GeoFence {

    private final int id;
    private final String name;
    private final int delay;
    private final Area area;

    public Location(int id, String name, int delay, double longitude, double latitude, int radius, Area area) {
        super(longitude, latitude, radius);
        this.id = id;
        this.name = name;
        this.delay = delay;
        this.area = area;
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

    public int getAreaId() {
        return area.getId();
    }

    public Area getArea() {
        return area;
    }
}
