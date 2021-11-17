package nl.han.aim.oosevt.lamport.data.dao.location;

import nl.han.aim.oosevt.lamport.data.entity.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LocationDAOImpl implements LocationDAO {
    @Override
    public void createLocation(String name, int delay, double longitude, double latitude, int radius, int areaId) {
    }

    @Override
    public Location getLocationById(int id) {
        return null;
    }

    @Override
    public ArrayList<Location> getLocations() {
        return null;
    }

    @Override
    public void updateLocation(int id, String name, int delay, double longitude, double latitude, int radius, int areaId) {
    }

    @Override
    public void deleteLocation(int id) {
    }
}
