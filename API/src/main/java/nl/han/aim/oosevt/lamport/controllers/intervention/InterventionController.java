package nl.han.aim.oosevt.lamport.controllers.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.InterventionResponseDTO;
import nl.han.aim.oosevt.lamport.services.intervention.InterventionService;
import nl.han.aim.oosevt.lamport.shared.Permission;
import nl.han.aim.oosevt.lamport.shared.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                interventionService.getInterventions(),
                HttpStatus.OK
        );
    }

    @PutMapping()
    @Permission(permission = Permissions.UPDATE_INTERVENTIONS)
    public void updateIntervention(@RequestBody UpdateCommandRequestDTO updateCommandRequestDTO) {
        interventionService.updateCommand(updateCommandRequestDTO);
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

    @PutMapping("/questionnaire")
    public void updateQuestionnaire(@RequestBody UpdateQuestionnaireRequestDTO questionnaire) {
        interventionService.updateQuestionnaire(questionnaire);
    }

    @PostMapping("/questionnaire")
    public void createQuestionnaire(@RequestBody CreateQuestionnaireRequestDTO createQuestionnaireRequestDTO) {
        interventionService.createQuestionnaire(createQuestionnaireRequestDTO);
    }

    @DeleteMapping("{id}")
    public void deleteIntervention(@PathVariable("id") int id) {
        interventionService.deleteIntervention(id);
    }
}
