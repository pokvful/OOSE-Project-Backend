package nl.han.aim.oosevt.lamport.controllers.intervention;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
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
            interventionService.getInterventions(),
            HttpStatus.OK
        );
    }


    @GetMapping("{id}")
    public ResponseEntity<InterventionResponseDTO> getIntervention(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                interventionService.getInterventionById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/command")
    public void updateCommand(@RequestBody UpdateCommandRequestDTO updateCommandRequestDTO) {
        interventionService.updateCommand(updateCommandRequestDTO);
    }

    @PostMapping("/command")
    public void createCommand(@RequestBody CreateCommandRequestDTO createCommandRequestDTO) {
        interventionService.createCommand(createCommandRequestDTO);
    }

    @PutMapping("/question")
    public void updateQuestion(@RequestBody UpdateQuestionRequestDTO question) {
        interventionService.updateQuestion(question);
    }

    @PostMapping("/question")
    public void createQuestion(@RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        interventionService.createQuestion(createQuestionRequestDTO);
    }
}
