package nl.han.aim.oosevt.lamport.controllers.user.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

import static java.util.Objects.isNull;

public class UserRequestDTO extends RequestDTO {
    private String username;
    private String email;
    private String password;
    private int roleId;

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

    public UserRequestDTO(String username, String email, String password, int roleId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public UserRequestDTO() {

    }

    @Override
    protected void validateDTO() {
        if (isNull(username) || username.isEmpty()) {
            addError("username", "Gebruikersnaam mag niet leeg zijn!");
        }
        if (isNull(email) || email.isEmpty()) {
            addError("email", "Email mag niet leeg zijn!");
        }
        if (isNull(password) || password.isEmpty()) {
            addError("password", "Wachtwoord mag niet leeg zijn!");
        }
        if (getRoleId() == 0) {
            addError("role", "Rol mag niet leeg zijn!");
        }
    }
}
