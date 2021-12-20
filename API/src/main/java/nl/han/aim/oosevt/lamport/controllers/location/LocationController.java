package nl.han.aim.oosevt.lamport.controllers.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.services.location.LocationService;
import nl.han.aim.oosevt.lamport.shared.Permission;
import nl.han.aim.oosevt.lamport.shared.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@CrossOrigin
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("")
    @Permission(permission = Permissions.GET_LOCATIONS)
    public ResponseEntity<List<LocationResponseDTO>> getLocations() {
        return new ResponseEntity<>(
                locationService.getLocations(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    @Permission(permission = Permissions.GET_LOCATIONS)
    public ResponseEntity<LocationResponseDTO> getLocation(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                locationService.getLocation(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    @Permission(permission = Permissions.DELETE_LOCATIONS)
    public void deleteLocation(@PathVariable("id") int id) {
        locationService.deleteLocation(id);
    }

    @PutMapping()
    @Permission(permission = Permissions.UPDATE_LOCATIONS)
    public void updateLocation(@RequestBody UpdateLocationRequestDTO updateLocationRequestDTO) {
        locationService.updateLocation(updateLocationRequestDTO);
    }

    @PostMapping()
    @Permission(permission = Permissions.CREATE_LOCATIONS)
    public void createLocation(@RequestBody CreateLocationRequestDTO location) {
        locationService.createLocation(location);
    }
}
