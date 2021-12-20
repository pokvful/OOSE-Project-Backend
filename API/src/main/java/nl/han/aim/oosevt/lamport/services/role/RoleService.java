package nl.han.aim.oosevt.lamport.services.role;

import nl.han.aim.oosevt.lamport.controllers.role.dto.CreateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.PermissionResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.UpdateRoleRequestDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getRoles();
    List<PermissionResponseDTO> getPermissions();
    void updateRole(UpdateRoleRequestDTO updateRoleRequestDTO);
    void deleteRole(int id);
    void createRole(CreateRoleRequestDTO createRoleRequestDTO);
    RoleResponseDTO getRoleById(int id);
}
