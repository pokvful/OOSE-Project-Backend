package nl.han.aim.oosevt.lamport.controllers.user.dto;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Goal;
import nl.han.aim.oosevt.lamport.data.entity.User;

public class UserResponseDTO {
    private final int id;
    private final String username;
    private final String email;
    private final RoleResponseDTO role;
    private final GoalResponseDTO goal;

    public UserResponseDTO(int id, String username, String email, RoleResponseDTO role, GoalResponseDTO goal) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.goal = goal;
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

    public GoalResponseDTO getGoal() {
        return goal;
    }

    public static UserResponseDTO fromData(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                RoleResponseDTO.fromData(user.getRole()),
                GoalResponseDTO.fromData(user.getGoal())
        );
    }
}
