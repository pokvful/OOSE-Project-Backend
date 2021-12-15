package nl.han.aim.oosevt.lamport.controllers.location;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.services.location.LocationService;
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
    public ResponseEntity<List<LocationResponseDTO>> getLocations() {
        return new ResponseEntity<>(
                locationService.getLocations(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<LocationResponseDTO> getLocation(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                locationService.getLocation(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public void deleteLocation(@PathVariable("id") int id) {
        locationService.deleteLocation(id);
    }

    @PutMapping()
    public void updateLocation(@RequestBody UpdateLocationRequestDTO updateLocationRequestDTO) {
        locationService.updateLocation(updateLocationRequestDTO);
    }

    @PostMapping()
    public void createLocation(@RequestBody CreateLocationRequestDTO location) {
        locationService.createLocation(location);
    }
}
