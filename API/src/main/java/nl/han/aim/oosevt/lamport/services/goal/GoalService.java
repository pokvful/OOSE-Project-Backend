package nl.han.aim.oosevt.lamport.services.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.UpdateGoalRequestDTO;

import java.util.List;

public interface GoalService {
    List<GoalResponseDTO> getGoals();

    GoalResponseDTO getGoal(int id);

    void createGoal(CreateGoalRequestDTO createGoalRequestDTO);

    void updateGoal(UpdateGoalRequestDTO updateGoalRequestDTO);
}
