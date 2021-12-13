package nl.han.aim.oosevt.lamport.controllers.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.CreateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.UpdateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.services.franchise.FranchiseService;
import nl.han.aim.oosevt.lamport.shared.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franchise")
@CrossOrigin
public class FranchiseController {
    private final FranchiseService franchiseService;

    @Autowired
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @GetMapping("")
    public ResponseEntity<List<FranchiseResponseDTO>> getFranchises() {
        return new ResponseEntity<>(
                franchiseService.getFranchises(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<FranchiseResponseDTO> getFranchise(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                franchiseService.getFranchiseById(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public void deleteFranchise(@PathVariable("id") int id) {
        franchiseService.deleteFranchise(id);
    }

    @PutMapping()
    public void updateFranchise(@RequestBody UpdateFranchiseRequestDTO updateFranchiseRequestDTO) {
        franchiseService.updateFranchise(updateFranchiseRequestDTO);
    }

    @PostMapping()
    public void createFranchise(@RequestBody CreateFranchiseRequestDTO createFranchiseRequestDTO) {
        franchiseService.createFranchise(createFranchiseRequestDTO);
    }
}
