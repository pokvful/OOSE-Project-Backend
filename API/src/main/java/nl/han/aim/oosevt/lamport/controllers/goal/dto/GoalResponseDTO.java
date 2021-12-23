package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.data.entity.Goal;

import java.util.List;
import java.util.stream.Collectors;

public class GoalResponseDTO {
    private final int id;
    private final String name;
    private final List<ProfileQuestionResponseDTO> profileQuestions;

    public GoalResponseDTO(int id, String name, List<ProfileQuestionResponseDTO> profileQuestions) {
        this.id = id;
        this.name = name;
        this.profileQuestions = profileQuestions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProfileQuestionResponseDTO> getProfileQuestions() {
        return profileQuestions;
    }

    public static GoalResponseDTO fromData(Goal goal) {
        return new GoalResponseDTO(
                goal.getGoalId(),
                goal.getGoalName(),
                goal
                        .getProfileQuestions()
                        .stream()
                        .map(ProfileQuestionResponseDTO::fromData)
                        .collect(Collectors.toList())
        );
    }
}
