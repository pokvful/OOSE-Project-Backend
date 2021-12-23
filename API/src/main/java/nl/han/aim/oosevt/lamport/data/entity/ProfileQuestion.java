package nl.han.aim.oosevt.lamport.data.entity;

public class ProfileQuestion {
    private final int profileQuestionId;
    private final String profileQuestionName;

    public ProfileQuestion(int profileQuestionId, String profileQuestionName) {
        this.profileQuestionId = profileQuestionId;
        this.profileQuestionName = profileQuestionName;
    }

    public int getProfileQuestionId() {
        return profileQuestionId;
    }

    public String getProfileQuestionName() {
        return profileQuestionName;
    }
}
