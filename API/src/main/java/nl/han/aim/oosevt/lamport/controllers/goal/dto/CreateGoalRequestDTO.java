package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import java.util.List;

public class CreateGoalRequestDTO extends GoalRequestDTO {
    public CreateGoalRequestDTO(String name, List<ProfileQuestionRequestDTO> profileQuestions) {
        super(name, profileQuestions);
    }

    public CreateGoalRequestDTO() {}
}
