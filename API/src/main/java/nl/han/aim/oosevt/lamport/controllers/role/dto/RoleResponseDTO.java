package nl.han.aim.oosevt.lamport.controllers.role.dto;

import nl.han.aim.oosevt.lamport.data.entity.Role;

public class RoleResponseDTO {
    private int id;
    private String name;

    public RoleResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleResponseDTO fromData(Role role) {
        this.id = role.getRoleId();
        this.name = role.getRoleName();

        return this;
    }
}
