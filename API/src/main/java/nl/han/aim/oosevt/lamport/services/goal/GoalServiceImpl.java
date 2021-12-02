package nl.han.aim.oosevt.lamport.services.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
import nl.han.aim.oosevt.lamport.data.dao.goal.GoalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoalServiceImpl implements GoalService {
    private GoalDAO goalDAO;

    @Autowired
    public GoalServiceImpl(GoalDAO goalDAO) {
        this.goalDAO = goalDAO;
    }

    @Override
    public List<GoalResponseDTO> getGoals() {
        return null;
    }

    @Override
    public GoalResponseDTO getGoal(int id) {
        return null;
    }

    @Override
    public void createGoal(CreateGoalRequestDTO goal) {
        goal.validate();
        goalDAO.createGoal(goal.getName());
    }
}
