package nl.han.aim.oosevt.lamport.controllers.user.dto;

import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.User;

public class UserResponseDTO {
    private int id;
    private String username;
    private String email;
    private RoleResponseDTO role;

    public UserResponseDTO(int id, String username, String email, RoleResponseDTO role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UserResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public RoleResponseDTO getRole() {
        return role;
    }

    public void setRole(RoleResponseDTO role) {
        this.role = role;
    }

    public UserResponseDTO fromData(User user) {
        this.email = user.getEmail();
        this.id = user.getUserId();
        this.role = new RoleResponseDTO().fromData(user.getRole());
        this.username = user.getUsername();

        return this;
    }
}
