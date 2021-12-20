package nl.han.aim.oosevt.lamport.controllers.goal.dto;

public class UpdateGoalRequestDTO extends GoalRequestDTO {
    private int id;

    public UpdateGoalRequestDTO(String name, int id) {
        super(name);
        this.id = id;
    }

    public UpdateGoalRequestDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}