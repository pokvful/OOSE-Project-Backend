package nl.han.aim.oosevt.lamport.controllers.goal.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class GoalRequestDTO extends RequestDTO {
    @NotEmpty
    protected String name;
    protected List<ProfileQuestionRequestDTO> profileQuestions = new ArrayList<>();

    public GoalRequestDTO(String name, List<ProfileQuestionRequestDTO> profileQuestions) {
        this.name = name;
        this.profileQuestions = profileQuestions;
    }

    public GoalRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProfileQuestionRequestDTO> getProfileQuestions() {
        return profileQuestions;
    }

    public void setProfileQuestions(List<ProfileQuestionRequestDTO> profileQuestions) {
        this.profileQuestions = profileQuestions;
    }
}
