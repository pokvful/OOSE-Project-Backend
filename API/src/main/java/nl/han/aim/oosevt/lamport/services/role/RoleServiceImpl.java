package nl.han.aim.oosevt.lamport.services.role;

import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.UpdateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDAO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    private void assertValidRole(int id) {
        if (this.roleDAO.getRoleById(id) == null) {
            throw new NotFoundException();
        }
    }

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
    public void deleteRole(int id) {
        assertValidRole(id);

        if(roleDAO.getUsersByRoleId(id) > 0) {
            throw new InvalidDTOException();
        }

        this.roleDAO.deleteRole(id);
    }
}
