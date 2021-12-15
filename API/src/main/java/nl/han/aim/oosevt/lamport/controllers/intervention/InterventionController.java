package nl.han.aim.oosevt.lamport.controllers.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.InterventionResponseDTO;
import nl.han.aim.oosevt.lamport.services.intervention.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/interventions")
@CrossOrigin
public class InterventionController {
    private final InterventionService interventionService;

    @Autowired
    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @GetMapping("")
    public ResponseEntity<List<InterventionResponseDTO>> getInterventions() {
        return new ResponseEntity<>(
                getMockInterventions(),
                HttpStatus.OK);
    }

    @PutMapping()
    public void updateIntervention(@RequestBody UpdateCommandRequestDTO updateCommandRequestDTO) {
        interventionService.updateCommand(updateCommandRequestDTO);
    }

    private List<InterventionResponseDTO> getMockInterventions() {
        final ArrayList<InterventionResponseDTO> interventionResponseDTOS = new ArrayList<>();

        interventionResponseDTOS.add(new InterventionResponseDTO(1, "Saladebar"));
        interventionResponseDTOS.add(new InterventionResponseDTO(2, "Hardlopen"));
        interventionResponseDTOS.add(new InterventionResponseDTO(3, "Opdrukken"));
        interventionResponseDTOS.add(new InterventionResponseDTO(4, "Kerk bekijken"));

        return interventionResponseDTOS;
    }

    @PostMapping("/command")
    public void createCommand(@RequestBody CreateCommandRequestDTO createCommandRequestDTO) {
        interventionService.createCommand(createCommandRequestDTO);
    }

    @PostMapping("/question")
    public void createQuestion(@RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        interventionService.createQuestion(createQuestionRequestDTO);
    }
}
