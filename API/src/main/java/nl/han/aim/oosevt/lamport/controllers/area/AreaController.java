package nl.han.aim.oosevt.lamport.controllers.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import nl.han.aim.oosevt.lamport.services.area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/areas")
@CrossOrigin
public class AreaController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("")
    public ResponseEntity<List<AreaResponseDTO>> getAreas() {
        return new ResponseEntity<>(
                areaService.getAreas(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<AreaResponseDTO> getArea(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                areaService.getArea(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public void deleteArea(@PathVariable("id") int id) {
        areaService.deleteArea(id);
    }

    @PutMapping()
    public void updateArea(@RequestBody UpdateAreaRequestDTO updateAreaRequestDTO) {
        areaService.updateArea(updateAreaRequestDTO);
    }

    @PostMapping()
    public void createArea(@RequestBody CreateAreaRequestDTO createAreaRequestDTO) {
        areaService.createArea(createAreaRequestDTO);
    }
}
