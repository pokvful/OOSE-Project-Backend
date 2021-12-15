package nl.han.aim.oosevt.lamport.controllers.role.dto;

import nl.han.aim.oosevt.lamport.data.entity.Role;

import java.util.List;

public class RoleResponseDTO {
    private final int id;
    private final String name;
    private final List<String> allowedPermissions;

    public RoleResponseDTO(int id, String name, List<String> allowedPermissions) {
        this.id = id;
        this.name = name;
        this.allowedPermissions = allowedPermissions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getAllowedPermissions() {
        return allowedPermissions;
    }

    public static RoleResponseDTO fromData(Role role) {
        return new RoleResponseDTO(
                role.getRoleId(),
                role.getRoleName(),
                role.getAllowedPermissions());
    }
}
