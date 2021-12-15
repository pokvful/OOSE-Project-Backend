package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.data.entity.Goal;

public class GoalResponseDTO {
    private final int id;
    private final String name;

    public GoalResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static GoalResponseDTO fromData(Goal goal) {
        return new GoalResponseDTO(
                goal.getGoalId(),
                goal.getGoalName()
        );
    }
}
