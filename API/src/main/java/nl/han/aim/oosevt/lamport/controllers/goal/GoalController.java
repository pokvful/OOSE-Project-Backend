package nl.han.aim.oosevt.lamport.controllers.goal;

import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.services.goal.GoalService;
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
    public ResponseEntity<List<GoalResponseDTO>> getGoals() {
        return new ResponseEntity<>(
                goalService.getGoals(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<GoalResponseDTO> getGoal(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                goalService.getGoal(id),
                HttpStatus.OK
        );
    }

    @PutMapping()
    public void updateGoal(@RequestBody UpdateGoalRequestDTO updateGoalRequestDTO) {
        areaService.updateArea(updateAreaRequestDTO);
    }
}
