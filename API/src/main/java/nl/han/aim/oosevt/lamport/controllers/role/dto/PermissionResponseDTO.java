package nl.han.aim.oosevt.lamport.controllers.role.dto;

import nl.han.aim.oosevt.lamport.shared.Permissions;

public class PermissionResponseDTO {
    private final String key;
    private final String display;

    public PermissionResponseDTO(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static PermissionResponseDTO fromEnum(Permissions permission) {
        return new PermissionResponseDTO(
                permission.name(),
                permission.getDisplay()
        );
    }
}
