package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import java.util.List;

public class UpdateGoalRequestDTO extends GoalRequestDTO {
    private int id;

    public UpdateGoalRequestDTO(String name, List<ProfileQuestionRequestDTO> profileQuestions, int id) {
        super(name, profileQuestions);
        this.id = id;
    }

    public UpdateGoalRequestDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
