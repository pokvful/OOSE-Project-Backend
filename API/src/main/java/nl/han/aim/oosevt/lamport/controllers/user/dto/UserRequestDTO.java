package nl.han.aim.oosevt.lamport.controllers.user.dto;

import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public class UserRequestDTO extends RequestDTO {
    protected String username;
    protected String email;
    protected String password;
    protected Role role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRequestDTO(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserRequestDTO() {

    }

    @Override
    protected void validateDTO() {
        if (username.isEmpty()) {
            addError("username", "Gebruikersnaam mag niet leeg zijn!");
        }
        if (email.isEmpty()) {
            addError("email", "Email mag niet leeg zijn!");
        }
        if (password.isEmpty()) {
            addError("password", "Wachtwoord mag niet leeg zijn!");
        }
        if (role.getRoleName().isEmpty()) {
            addError("role", "Rol mag niet leeg zijn!");
        }
        validateSpecificDTO();
    }

    protected void validateSpecificDTO() {
    }
}
