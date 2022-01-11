package nl.han.aim.oosevt.lamport.controllers.user.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

public class UserRequestDTO extends RequestDTO {
    @NotEmpty
    @TranslatedName(name = "Gebruikersnaam")
    private String username;

    @NotEmpty
    @TranslatedName(name = "Email")
    private String email;

    @NotEmpty
    @TranslatedName(name = "Wachtwoord")
    private String password;

    @NotEmpty
    private int roleId;

//    @NotEmpty
    private int goalId = 1;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public UserRequestDTO(String username, String email, String password, int roleId, int goalId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.goalId = goalId;
    }

    public UserRequestDTO() {

    }
}
