package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.data.entity.*;

public class ProfileQuestionResponseDTO {
    private final int id;
    private final String name;

    public ProfileQuestionResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProfileQuestionResponseDTO fromData(ProfileQuestion profileQuestion) {
        return new ProfileQuestionResponseDTO(
                profileQuestion.getProfileQuestionId(),
                profileQuestion.getProfileQuestionName()
        );
    }
}
