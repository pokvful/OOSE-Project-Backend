package nl.han.aim.oosevt.lamport.data.entity;

public class Goal {
    private final int goalId;
    private final String goalName;

    public Goal(int goalId, String goalName) {
        this.goalId = goalId;
        this.goalName = goalName;
    }

    public int getGoalId() { return goalId; }

    public String getGoalName() { return goalName; }

}
