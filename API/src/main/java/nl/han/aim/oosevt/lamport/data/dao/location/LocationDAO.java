package nl.han.aim.oosevt.lamport.data.dao.location;

public interface LocationDAO {
    void createLocation(String name, int delay, double longitude, double latitude, int radius);
    Location getLocationById(int id);
    ArrayList<Location> getLocations();
    void updateLocation(int id, String name, int delay, double longitude, double latitude, int radius);
    void deleteLocation(int id);
}
