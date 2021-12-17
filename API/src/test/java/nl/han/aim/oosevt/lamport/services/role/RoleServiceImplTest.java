package nl.han.aim.oosevt.lamport.services.role;

import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDAO;
import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleServiceImplTest {

    private final int invalidRoleId = 0;

    private final int deleteRoleId = 1;

    private String roleName = "Onderzoeker";

    private ArrayList<String> allowedPermissions;

    private RoleServiceImpl sut;

    private RoleDAO roleDAOFixture;

    @BeforeEach
    public void setup() {

        roleDAOFixture = Mockito.mock(RoleDAO.class);


        sut = new RoleServiceImpl(roleDAOFixture);

        allowedPermissions = new ArrayList<>();

        allowedPermissions.add("OnderzoekersRechten");

    }

    @Test
    public void testDeleteRole() {
        // Arrange
        Mockito.when(roleDAOFixture.getRoleById(Mockito.anyInt())).thenReturn(new Role(deleteRoleId, roleName, allowedPermissions));
        Mockito.doNothing().when(this.roleDAOFixture).deleteRole(Mockito.anyInt());

        // Act
        sut.deleteRole(deleteRoleId);

        // Assert
        Mockito.verify(roleDAOFixture).deleteRole(deleteRoleId);
    }

    @Test
    public void testDeleteRoleThrowsException() {
        assertThrows(NotFoundException.class, () -> sut.deleteRole(invalidRoleId));
    }
}
