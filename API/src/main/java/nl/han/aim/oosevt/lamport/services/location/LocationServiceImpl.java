package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationServiceImpl implements LocationService{
    @Override
    public void createLocation(CreateLocationRequestDTO createLocationRequestDTO) {

    }

    @Override
    public void updateLocation(UpdateLocationRequestDTO updateLocationRequestDTO) {

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
