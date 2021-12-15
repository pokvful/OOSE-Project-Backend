package nl.han.aim.oosevt.lamport.controllers.user.dto;

import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.User;

public class UserResponseDTO {
    private final int id;
    private final String username;
    private final String email;
    private final RoleResponseDTO role;

    public UserResponseDTO(int id, String username, String email, RoleResponseDTO role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public RoleResponseDTO getRole() {
        return role;
    }

    public static UserResponseDTO fromData(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                RoleResponseDTO.fromData(user.getRole())
        );
    }
}
