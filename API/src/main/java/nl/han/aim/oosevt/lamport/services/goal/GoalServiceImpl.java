package nl.han.aim.oosevt.lamport.services.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.UpdateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.goal.GoalDAO;
import nl.han.aim.oosevt.lamport.data.entity.Goal;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoalServiceImpl implements GoalService {
    private final GoalDAO goalDAO;

    @Autowired
    public GoalServiceImpl(GoalDAO goalDAO) {
        this.goalDAO = goalDAO;
    }

    private void assertGoalExists(int id) {
        if (goalDAO.getGoalById(id) == null) {
            throw new NotFoundException();
        }
    }

    @Override
    public List<GoalResponseDTO> getGoals() {
        return goalDAO
                .getGoals()
                .stream()
                .map(GoalResponseDTO::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public GoalResponseDTO getGoal(int id) {
        final Goal goal = goalDAO.getGoalById(id);
        if(goal == null) {
            throw new NotFoundException();
        }
        return GoalResponseDTO.fromData(goal);
    }

    @Override
    public void createGoal(CreateGoalRequestDTO goal) {
        goal.validate();
        goalDAO.createGoal(goal.getName());
    }

    @Override
    public void updateGoal(UpdateGoalRequestDTO updateGoalRequestDTO) {
        updateGoalRequestDTO.validate();
        assertGoalExists(updateGoalRequestDTO.getId());

        goalDAO.updateGoal(updateGoalRequestDTO.getId(), updateGoalRequestDTO.getName());
    }

    @Override
    public void deleteGoal(int id) {
        assertGoalExists(id);
        goalDAO.deleteGoal(id);
    }
}
