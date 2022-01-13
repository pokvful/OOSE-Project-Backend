package nl.han.aim.oosevt.lamport.data.dao;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDataMapper;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDataMapperImpl;
import nl.han.aim.oosevt.lamport.data.entity.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDataMapperTests {

    private final RoleDataMapper sut = new RoleDataMapperImpl();

    @Test
    void roleMapperMapsDataCorrectly() throws SQLException {
        //Arrange
        final int roleId = 1;
        final String roleName = "Test";

        final ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("role_id")).thenReturn(roleId);
        Mockito.when(resultSet.getString("role_name")).thenReturn(roleName);

        final Role expected = new Role(roleId, roleName, new ArrayList<>());

        //Act
        final Role actual =  sut.getFromResultSet(resultSet);

        //Assert
        ObjectAssertions.assertEquals(expected, actual);
    }
}
