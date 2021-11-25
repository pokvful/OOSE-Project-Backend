package nl.han.aim.oosevt.lamport.controllers.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.services.franchise.FranchiseService;
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
                franchiseService.getFranchise(id),
                HttpStatus.OK
        );
    }
}
