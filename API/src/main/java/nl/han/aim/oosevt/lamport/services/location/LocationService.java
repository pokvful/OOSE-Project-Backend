package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;

import java.util.List;

public interface LocationService {
    void createLocation(CreateLocationRequestDTO location);
    void updateLocation(UpdateLocationRequestDTO newData);
    void deleteLocation(int id);
    LocationResponseDTO getLocation(int id);
    List<LocationResponseDTO> getLocations();
}
