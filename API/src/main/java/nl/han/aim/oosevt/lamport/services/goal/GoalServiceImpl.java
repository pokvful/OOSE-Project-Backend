package nl.han.aim.oosevt.lamport.services.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoalServiceImpl implements GoalService {
    @Override
    public List<GoalResponseDTO> getGoals() {
        return null;
    }

    @Override
    public GoalResponseDTO getGoal(int id) {
        return null;
    }
}
