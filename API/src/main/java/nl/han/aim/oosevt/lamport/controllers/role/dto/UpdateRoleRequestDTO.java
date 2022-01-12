package nl.han.aim.oosevt.lamport.controllers.role.dto;

import java.util.List;

public class UpdateRoleRequestDTO extends RoleRequestDTO {
    private int id;

    public UpdateRoleRequestDTO(int id, List<String> allowedPermissions, String name) {
        super(allowedPermissions, name);
        this.id = id;
    }

    public UpdateRoleRequestDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
