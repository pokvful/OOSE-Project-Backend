package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.data.entity.Goal;

public class GoalResponseDTO {
    private int id;
    private String name;

    public GoalResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GoalResponseDTO() {
    }

    public GoalResponseDTO fromData(Goal goal) {
        this.id = goal.getGoalId();
        this.name = goal.getGoalName();

        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
