package nl.han.aim.oosevt.lamport.data.entity;

import java.util.List;

public class Role {
    private final int roleId;
    private final String roleName;
    private final List<String> allowedPermissions;

    public Role(int roleId, String roleName, List<String> allowedPermissions) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.allowedPermissions = allowedPermissions;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<String> getAllowedPermissions() {
        return allowedPermissions;
    }
}
