package nl.han.aim.oosevt.lamport.services.role;

import nl.han.aim.oosevt.lamport.controllers.role.dto.CreateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.PermissionResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.UpdateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDAO;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.shared.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        return roleDAO
                .getRoles()
                .stream()
                .map(RoleResponseDTO::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionResponseDTO> getPermissions() {
        return Arrays
                .stream(Permissions.values())
                .map(PermissionResponseDTO::fromEnum)
                .collect(Collectors.toList());
    }

    @Override
    public void updateRole(UpdateRoleRequestDTO updateRoleRequestDTO) {
        if (roleDAO.getRoleById(updateRoleRequestDTO.getId()) == null) {
            throw new NotFoundException();
        }

        roleDAO.updateRole(
                updateRoleRequestDTO.getId(),
                updateRoleRequestDTO.getName(),
                updateRoleRequestDTO.getAllowedPermissions()
        );
    }

    @Override
    public void createRole(CreateRoleRequestDTO createRoleRequestDTO) {
        roleDAO.createRole(
                createRoleRequestDTO.getName(),
                createRoleRequestDTO.getAllowedPermissions()
        );
    }

    @Override
    public RoleResponseDTO getRoleById(int id) {
        return RoleResponseDTO.fromData(roleDAO.getRoleById(id));
    }
}
