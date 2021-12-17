package nl.han.aim.oosevt.lamport.controllers.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.InterventionResponseDTO;
import nl.han.aim.oosevt.lamport.services.intervention.InterventionService;
import nl.han.aim.oosevt.lamport.shared.Permission;
import nl.han.aim.oosevt.lamport.shared.Permissions;
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
    @Permission(permission = Permissions.GET_INTERVENTIONS)
    public ResponseEntity<List<InterventionResponseDTO>> getInterventions() {
        return new ResponseEntity<>(
                getMockInterventions(),
                HttpStatus.OK);
    }

    @PutMapping()
    @Permission(permission = Permissions.UPDATE_INTERVENTIONS)
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
    @Permission(permission = Permissions.CREATE_INTERVENTIONS)
    public void createCommand(@RequestBody CreateCommandRequestDTO createCommandRequestDTO) {
        interventionService.createCommand(createCommandRequestDTO);
    }

    @PutMapping("/question")
    @Permission(permission = Permissions.UPDATE_INTERVENTIONS)
    public void updateQuestion(@RequestBody UpdateQuestionRequestDTO question) {
        interventionService.updateQuestion(question);
    }

    @PostMapping("/question")
    @Permission(permission = Permissions.CREATE_INTERVENTIONS)
    public void createQuestion(@RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        interventionService.createQuestion(createQuestionRequestDTO);
    }
}
