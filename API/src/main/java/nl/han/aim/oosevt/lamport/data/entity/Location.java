package nl.han.aim.oosevt.lamport.data.entity;

public class Location extends GeoFence {

    private final int id;
    private final String name;
    private final int delay;
    private final int areaId;

    public Location(int id, String name, int delay, double longitude, double latitude, int radius, int areaId) {
        super(longitude, latitude, radius);
        this.id = id;
        this.name = name;
        this.delay = delay;
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

    public int getAreaId() {
        return areaId;
    }
}
