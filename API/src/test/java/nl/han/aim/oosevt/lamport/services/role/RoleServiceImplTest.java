package nl.han.aim.oosevt.lamport.services.role;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.controllers.role.dto.CreateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.PermissionResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.UpdateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDAO;
import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.shared.Permissions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleServiceImplTest {

    private final int invalidRoleId = 0;
    private final int roleId = 1;
    private final int updateRoleId = 1;
    private final String updateRoleName = "updated";
    private String roleName = "Onderzoeker";
    private ArrayList<String> allowedPermissions;
    private RoleServiceImpl sut;

    private RoleDAO roleDAOFixture;
    private RoleResponseDTO mockRoleResponseDto;
    private final List<RoleResponseDTO> mockRolesResponse = new ArrayList<>();
    private final Role mockRole = new Role(roleId, roleName, new ArrayList<>());
    private final List<Role> mockRoles = new ArrayList<>();
    private UpdateRoleRequestDTO updateRoleRequestDto;
    private final ArrayList<String> updateRolePermissions = new ArrayList<>();
    private final ArrayList<String> createRolePermissions = new ArrayList<>();
    private final String createRoleName = "create";
    private CreateRoleRequestDTO createRoleRequestDto;

    @BeforeEach
    public void setup() {

        roleDAOFixture = Mockito.mock(RoleDAO.class);

        sut = new RoleServiceImpl(roleDAOFixture);
        allowedPermissions = new ArrayList<>();
        allowedPermissions.add("OnderzoekersRechten");
        mockRoleResponseDto = new RoleResponseDTO(roleId, roleName, new ArrayList<>());
        mockRolesResponse.clear();
        mockRolesResponse.add(mockRoleResponseDto);
        mockRoles.clear();
        mockRoles.add(mockRole);
        createRoleRequestDto =  Mockito.spy(new CreateRoleRequestDTO(createRolePermissions, createRoleName));
        updateRoleRequestDto = Mockito.spy(new UpdateRoleRequestDTO(updateRoleId, new ArrayList<>(), updateRoleName));
    }

    @Test
    public void deleteRoleWorksWithValidIds() {
        // Arrange
        Mockito.when(roleDAOFixture.getRoleById(Mockito.anyInt())).thenReturn(mockRole);
        Mockito.doNothing().when(this.roleDAOFixture).deleteRole(roleId);

        // Act
        sut.deleteRole(roleId);

        // Assert
        Mockito.verify(roleDAOFixture).deleteRole(roleId);
    }

    @Test
    public void deleteRoleThrowsExceptionWhenInvalidRoleId() {
        assertThrows(NotFoundException.class, () -> sut.deleteRole(invalidRoleId));
    }

    @Test
    public void deleteRoleThrowsExceptionWhenInvalidUsersInRole() {
        Mockito.when(roleDAOFixture.getUserCountByRoleId(roleId)).thenReturn(2);
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);

        assertThrows(InvalidDTOException.class, () -> sut.deleteRole(roleId));
    }

    @Test
    void getRoleCallsDAO() {
        // Arrange
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);

        // Act
        sut.getRoleById(roleId);

        // Assert
        Mockito.verify(roleDAOFixture).getRoleById(roleId);
    }

    @Test
    void getExistingRole() {
        // Arrange
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);

        // Act
        RoleResponseDTO actual = sut.getRoleById(roleId);

        // Assert
        ObjectAssertions.assertEquals(mockRoleResponseDto, actual);
    }

    @Test
    void getNoRole() {
        // Arrange
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(null);

        // Assert
        Assertions.assertThrows(NotFoundException.class,
                () -> sut.getRoleById(roleId));
    }

    @Test
    void getRolesCallsDAO() {
        // Act
        sut.getRoles();

        // Assert
        Mockito.verify(roleDAOFixture).getRoles();
    }

    @Test
    void getExistingRoles() {
        // Arrange
        Mockito.when(roleDAOFixture.getRoles()).thenReturn(mockRoles);

        // Act
        List<RoleResponseDTO> actual = sut.getRoles();

        // Assert
        ObjectAssertions.assertEquals(mockRolesResponse, actual);
    }

    @Test
    void getNoRoles() {
        // Arrange
        mockRoles.clear();
        mockRolesResponse.clear();

        Mockito.when(roleDAOFixture.getRoles()).thenReturn(mockRoles);

        // Act
        List<RoleResponseDTO> actual = sut.getRoles();

        // Assert
        ObjectAssertions.assertEquals(mockRolesResponse, actual);
    }

    @Test
    void updateRoleHappy() {
        //Arrange
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);

        //Act
        sut.updateRole(updateRoleRequestDto);

        //Assert
        Mockito.verify(roleDAOFixture).updateRole(updateRoleId, updateRoleName, updateRolePermissions);
    }

    @Test
    public void updateRoleVerifies() {
        // Arrange
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);
        // Act
        sut.updateRole(updateRoleRequestDto);

        // Assert
        Mockito.verify(updateRoleRequestDto).validate();
    }

    @Test
    void updateRoleNotFound() {
        //Arrange
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(null);

        //Act
        Executable act = () -> sut.updateRole(updateRoleRequestDto);

        //Assert
        Assertions.assertThrows(NotFoundException.class, act);
    }

    @Test
    public void createRoleVerifies() {
        // Arrange
        // Act
        sut.createRole(createRoleRequestDto);

        // Assert
        Mockito.verify(createRoleRequestDto).validate();
    }

    @Test
    public void createRoleCallsDAO() {
        // Act
        sut.createRole(createRoleRequestDto);

        // Assert
        Mockito.verify(roleDAOFixture)
                .createRole(createRoleName, createRolePermissions);
    }

    @Test
    public void getPermissionsReturnsPermissions() {
        // Act
        final List<PermissionResponseDTO> actual = sut.getPermissions();

        // Assert
        ObjectAssertions.assertEquals(actual, Arrays.stream(Permissions.values()).map(x -> new PermissionResponseDTO(x.name(), x.getDisplay())).collect(Collectors.toList()));
    }

}
