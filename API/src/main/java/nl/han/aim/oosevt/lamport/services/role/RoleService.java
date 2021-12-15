package nl.han.aim.oosevt.lamport.services.role;

import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.UpdateRoleRequestDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getRoles();
    void updateRole(UpdateRoleRequestDTO updateRoleRequestDTO);
}
