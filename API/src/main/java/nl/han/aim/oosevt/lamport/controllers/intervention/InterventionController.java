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

        interventionResponseDTOS.add(new InterventionResponseDTO(1, "Test1"));
        interventionResponseDTOS.add(new InterventionResponseDTO(2, "Test2"));
        interventionResponseDTOS.add(new InterventionResponseDTO(3, "Test3"));
        interventionResponseDTOS.add(new InterventionResponseDTO(4, "Test4"));
        interventionResponseDTOS.add(new InterventionResponseDTO(5, "Test5"));
        interventionResponseDTOS.add(new InterventionResponseDTO(6, "Test6"));
        interventionResponseDTOS.add(new InterventionResponseDTO(7, "Test7"));

        return interventionResponseDTOS;
    }
}
