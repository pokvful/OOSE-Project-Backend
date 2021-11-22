package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationServiceImpl implements LocationService {
    private final LocationDAO locationDAO;
    private final AreaDAO areaDAO;
    private final InterventionDAO interventionDAO;

    @Autowired
    public LocationServiceImpl(LocationDAO locationDAO, AreaDAO areaDAO, InterventionDAO interventionDAO) {
        this.locationDAO = locationDAO;
        this.areaDAO = areaDAO;
        this.interventionDAO = interventionDAO;
    }

    private void assertValidArea(int areaId) {
        if (areaDAO.getAreaById(areaId) == null) {
            throw new NotFoundException();
        }
    }

    private void assertGeldigeLocation(int locationId) {
        if (locationDAO.getLocationById(locationId) == null) {
            throw new NotFoundException();
        }
    }

    @Override
    public void createLocation(CreateLocationRequestDTO location) {
        location.validate();

        int areaId = location.getAreaId();

        assertValidArea(areaId);

        locationDAO.createLocation(location.getName(), location.getDelay(), location.getLongitude(),
                location.getLatitude(), location.getRadius(), areaId, location.getLinkedInterventions());
    }

    @Override
    public void updateLocation(UpdateLocationRequestDTO newData) {
        newData.validate();

        int id = newData.getLocationId();
        int areaId = newData.getAreaId();

        assertGeldigeLocation(id);
        assertValidArea(areaId);

        locationDAO.updateLocation(newData.getLocationId(), newData.getName(), newData.getDelay(),
                newData.getLongitude(), newData.getLatitude(), newData.getRadius(), areaId, newData.getLinkedInterventions());
    }

    @Override
    public void deleteLocation(int id) {
        if (locationDAO.getLocationById(id) == null) {
            throw new NotFoundException();
        }
        locationDAO.deleteLocation(id);
    }

    @Override
    public LocationResponseDTO getLocation(int id) {
        final Location location = locationDAO.getLocationById(id);

        if (location == null) {
            throw new NotFoundException();
        }

        return new LocationResponseDTO().fromData(location);
    }

    @Override
    public List<LocationResponseDTO> getLocations() {
        return locationDAO.getLocations().stream()
                .map((locationEntity) -> new LocationResponseDTO().fromData(locationEntity))
                .collect(Collectors.toList());
    }
}
