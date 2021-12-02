package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.data.entity.Franchise;

public interface GoalDAO {
    void createGoal(String name);
    Franchise getGoalById(int goalId);
    List<Goal> getGoal();
    void updateGoal(int goalId, String name);
    void deleteGoal(int goalId);
}
