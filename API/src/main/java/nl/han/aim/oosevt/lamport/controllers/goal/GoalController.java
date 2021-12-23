package nl.han.aim.oosevt.lamport.controllers.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.UpdateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.services.goal.GoalService;
import nl.han.aim.oosevt.lamport.shared.Permission;
import nl.han.aim.oosevt.lamport.shared.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@CrossOrigin
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("")
    @Permission(permission = Permissions.GET_GOALS)
    public ResponseEntity<List<GoalResponseDTO>> getGoals() {
        return new ResponseEntity<>(
                goalService.getGoals(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    @Permission(permission = Permissions.GET_GOALS)
    public ResponseEntity<GoalResponseDTO> getGoal(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                goalService.getGoal(id),
                HttpStatus.OK
        );
    }

    @PutMapping()
    @Permission(permission = Permissions.UPDATE_GOALS)
    public void updateGoal(@RequestBody UpdateGoalRequestDTO updateGoalRequestDTO) {
        goalService.updateGoal(updateGoalRequestDTO);
    }

    @PostMapping()
    @Permission(permission = Permissions.CREATE_GOALS)
    public void createGoal(@RequestBody CreateGoalRequestDTO createGoalRequestDTO) {
        goalService.createGoal(createGoalRequestDTO);
    }

    @DeleteMapping("{id}")
    @Permission(permission = Permissions.DELETE_GOALS)
    public void deleteGoal(@PathVariable("id") int id) {
        goalService.deleteGoal(id);
    }
}
