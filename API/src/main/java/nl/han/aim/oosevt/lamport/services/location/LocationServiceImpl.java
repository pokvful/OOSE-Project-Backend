package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationServiceImpl implements LocationService {
    private LocationDAO dataAccess;

    @Autowired
    public LocationServiceImpl(LocationDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void createLocation(CreateLocationRequestDTO location) {
        location.validate();

        dataAccess.createLocation(
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

    }

    @Override
    public void deleteLocation(int id) {
        if (dataAccess.getLocationById(id) == null) {
            throw new NotFoundException();
        }
        dataAccess.deleteLocation(id);
    }

    @Override
    public LocationResponseDTO getLocation(int id) {
        final Location location = dataAccess.getLocationById(id);

        if (location == null) {
            throw new NotFoundException();
        }

        return new LocationResponseDTO().fromData(location);
    }

    @Override
    public List<LocationResponseDTO> getLocations() {
        return  dataAccess.getLocations().stream()
                .map((locationEntity) -> new LocationResponseDTO().fromData(locationEntity))
                .collect(Collectors.toList());
    }
}