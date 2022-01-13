package nl.han.aim.oosevt.lamport.controllers.role.dto;

import java.util.List;

public class CreateRoleRequestDTO extends RoleRequestDTO {
    public CreateRoleRequestDTO(List<String> allowedPermissions, String name) {
        super(allowedPermissions, name);
    }

    public CreateRoleRequestDTO() {}
}
