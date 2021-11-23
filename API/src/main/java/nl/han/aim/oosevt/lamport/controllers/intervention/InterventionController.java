package nl.han.aim.oosevt.lamport.controllers.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.InterventionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/interventions")
@CrossOrigin
public class InterventionController {
    @GetMapping("")
    public ResponseEntity<List<InterventionResponseDTO>> getInterventions() {
        return new ResponseEntity<>(
                getMockInterventions(),
                HttpStatus.OK
        );
    }

    private List<InterventionResponseDTO> getMockInterventions() {
        final ArrayList<InterventionResponseDTO> interventionResponseDTOS = new ArrayList<>();

        interventionResponseDTOS.add(new InterventionResponseDTO(1, "Saladebar"));
        interventionResponseDTOS.add(new InterventionResponseDTO(2, "Hardlopen"));
        interventionResponseDTOS.add(new InterventionResponseDTO(3, "Opdrukken"));
        interventionResponseDTOS.add(new InterventionResponseDTO(4, "Kerk bekijken"));

        return interventionResponseDTOS;
    }
}
