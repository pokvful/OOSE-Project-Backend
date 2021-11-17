package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
                location.getRadius(),
                location.getAreaId()
        );
    }

    @Override
    public void updateLocation(UpdateLocationRequestDTO newData) {
        newData.validate();

        locationDAO.updateLocation(
                newData.getId(),
                newData.getName(),
                newData.getDelay(),
                newData.getLongitude(),
                newData.getLatitude(),
                newData.getRadius(),
                newData.getAreaId()
        );
    }

    @Override
    public void deleteLocation(int id) {

    }

    @Override
    public LocationResponseDTO getLocation(int id) {
        return null;
    }

    @Override
    public List<LocationResponseDTO> getLocations() {
        return null;
    }
}