package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationServiceImpl implements LocationService {
    private LocationDAO locationDAO;

    @Autowired
    public LocationServiceImpl(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public void createLocation(CreateLocationRequestDTO location) {
        location.validate();

        locationDAO.createLocation(
            location.getName(),
            location.getDelay(),
            location.getLongitude(),
            location.getLatitude(),
            location.getRadius()
        );
    }
}
