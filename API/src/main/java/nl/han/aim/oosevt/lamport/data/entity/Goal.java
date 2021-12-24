package nl.han.aim.oosevt.lamport.data.entity;

import java.util.List;

public class Goal {
    private final int goalId;
    private final String goalName;
    private final List<ProfileQuestion> profileQuestions;

    public Goal(int goalId, String goalName, List<ProfileQuestion> profileQuestions) {
        this.goalId = goalId;
        this.goalName = goalName;
        this.profileQuestions = profileQuestions;
    }

    public int getGoalId() {
        return goalId;
    }

    public String getGoalName() {
        return goalName;
    }

    public List<ProfileQuestion> getProfileQuestions() {
        return profileQuestions;
    }
}
