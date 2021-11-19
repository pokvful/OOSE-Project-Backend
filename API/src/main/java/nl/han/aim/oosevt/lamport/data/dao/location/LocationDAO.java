package nl.han.aim.oosevt.lamport.data.dao.location;

import nl.han.aim.oosevt.lamport.data.entity.Location;

import java.util.List;

public interface LocationDAO {
    void createLocation(String name, int delay, double longitude, double latitude, int radius, int areaId, List<Integer> linkedInterventions);
    Location getLocationById(int id);
    List<Location> getLocations();
    void updateLocation(int id, String name, int delay, double longitude, double latitude, int radius, int areaId, List<Integer> linkedInterventions);
    void deleteLocation(int id);
}
