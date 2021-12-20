package nl.han.aim.oosevt.lamport.controllers.role.dto;

import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.shared.Permissions;

import java.util.List;
import java.util.stream.Collectors;

public class RoleResponseDTO {
    private final int id;
    private final String name;
    private final List<PermissionResponseDTO> allowedPermissions;

    public RoleResponseDTO(int id, String name, List<PermissionResponseDTO> allowedPermissions) {
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

    public List<PermissionResponseDTO> getAllowedPermissions() {
        return allowedPermissions;
    }

    public static RoleResponseDTO fromData(Role role) {
        return new RoleResponseDTO(
                role.getRoleId(),
                role.getRoleName(),
                role.getAllowedPermissions().stream().map(x -> {
                    final Permissions permission = Permissions.valueOf(x);

                    return new PermissionResponseDTO(permission.name(), permission.getDisplay());
                }).collect(Collectors.toList()));
    }
}
