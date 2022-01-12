package nl.han.aim.oosevt.lamport.controllers.role.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

import java.util.List;

public class RoleRequestDTO extends RequestDTO {
    private List<String> allowedPermissions;
    @NotEmpty
    @TranslatedName(name = "Naam")
    private String name;

    public RoleRequestDTO(List<String> allowedPermissions, String name) {
        this.allowedPermissions = allowedPermissions;
        this.name = name;
    }

    public RoleRequestDTO() {}

    public List<String> getAllowedPermissions() {
        return allowedPermissions;
    }

    public void setAllowedPermissions(List<String> allowedPermissions) { this.allowedPermissions = allowedPermissions; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }
}
