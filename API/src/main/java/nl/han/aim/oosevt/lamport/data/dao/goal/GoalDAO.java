package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.ProfileQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.data.entity.Goal;

import java.util.List;

public interface GoalDAO {
    void createGoal(String name, List<ProfileQuestionRequestDTO> questions);
    Goal getGoalById(int goalId);
    List<Goal> getGoals();
    void updateGoal(int goalId, String name, List<ProfileQuestionRequestDTO> questions);
    void deleteGoal(int goalId);
}
