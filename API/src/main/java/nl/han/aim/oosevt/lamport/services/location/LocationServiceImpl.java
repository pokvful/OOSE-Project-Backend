package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
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
    private final FranchiseDAO franchiseDAO;

    @Autowired
    public LocationServiceImpl(LocationDAO locationDAO, AreaDAO areaDAO, FranchiseDAO franchiseDAO) {
        this.locationDAO = locationDAO;
        this.areaDAO = areaDAO;
        this.franchiseDAO = franchiseDAO;
    }

    private void assertValidArea(int areaId) {
        if (areaDAO.getAreaById(areaId) == null) {
            throw new NotFoundException();
        }
    }

    private void assertValidLocation(int locationId) {
        if (locationDAO.getLocationById(locationId) == null) {
            throw new NotFoundException();
        }
    }

    @Override
    public void createLocation(CreateLocationRequestDTO location) {
        location.validate();

        int areaId = location.getAreaId();

        assertValidArea(areaId);

        int franchiseId = location.getFranchiseId();

        assertValidFranchise(franchiseId);

        locationDAO.createLocation(location.getName(), location.getDelay(), location.getLongitude(),
                location.getLatitude(), location.getRadius(), areaId, franchiseId, location.getLinkedInterventions());
    }

    private void assertValidFranchise(int franchiseId) {
        if (franchiseDAO.getFranchiseById(franchiseId) == null) {
            throw new NotFoundException();
        }
    }

    @Override
    public void updateLocation(UpdateLocationRequestDTO newData) {
        newData.validate();

        int id = newData.getId();
        int areaId = newData.getAreaId();
        int franchiseId = newData.getFranchiseId();

        assertValidLocation(id);
        assertValidArea(areaId);
        assertValidFranchise(franchiseId);

        locationDAO.updateLocation(newData.getId(), newData.getName(), newData.getDelay(),
                newData.getLongitude(), newData.getLatitude(), newData.getRadius(), areaId, franchiseId, newData.getLinkedInterventions());
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
                .map(locationEntity -> new LocationResponseDTO().fromData(locationEntity))
                .collect(Collectors.toList());
    }
}
