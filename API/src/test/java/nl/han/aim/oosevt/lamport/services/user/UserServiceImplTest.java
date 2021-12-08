package nl.han.aim.oosevt.lamport.services.user;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest {

    private final int userId = 1;
    private final String username = "Bart";
    private final int roleId = 1;
    private final String roleName = "Beheerder";
    private final String email = "b.barends@student.han.nl";
    private final String password = "SomePassword";

    private Role mockRole;
    private User mockUser;
    private UserResponseDTO mockUserResponseDTO;
    private RoleResponseDTO mockRoleResponseDTO;
    private ArrayList<UserResponseDTO> usersResponseDTO;
    private ArrayList<User> mockUsers;

    private UserServiceImpl sut;
    private UserDAO userDAOFixture;

    @BeforeEach
    public void setup() {
        mockRole = new Role(roleId, roleName);
        mockUser = new User(userId, username, email, password, mockRole);

        userDAOFixture = Mockito.mock(UserDAO.class);

        mockRoleResponseDTO = new RoleResponseDTO(roleId, roleName);
        mockUserResponseDTO = new UserResponseDTO(userId, username, email, mockRoleResponseDTO);
        mockUsers = new ArrayList<>();
        mockUsers.add(mockUser);

        usersResponseDTO = new ArrayList<>();
        usersResponseDTO.add(mockUserResponseDTO);

        sut = new UserServiceImpl(userDAOFixture);
    }

    @Test
    void getUserCallsDAO() {
        //Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(mockUser);

        //Act
        sut.getUserById(userId);

        //Assert
        Mockito.verify(userDAOFixture).getUserById(userId);
    }

    @Test
    void getExistingUser() {
        //Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(mockUser);

        //Act
        UserResponseDTO actual = sut.getUserById(userId);

        //Assert
        ObjectAssertions.assertEquals(mockUserResponseDTO, actual);
    }

    @Test
    void getNoUser() {
        //Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(null);

        //Assert
        Assertions.assertThrows(NotFoundException.class, () -> sut.getUserById(userId));
    }


    @Test
    void getUsersCallsDAO() {
        //Act
        sut.getUsers();

        //Assert
        Mockito.verify(userDAOFixture).getUsers();
    }

    @Test
    void getExistingUsers() {
        //Arrange
        Mockito.when(userDAOFixture.getUsers()).thenReturn(mockUsers);

        //Act
        List<UserResponseDTO> actual = sut.getUsers();

        //Assert
        ObjectAssertions.assertEquals(usersResponseDTO, actual);
    }

    @Test
    void getNoUsers() {
        //Arrange
        usersResponseDTO.clear();
        mockUsers.clear();

        Mockito.when(userDAOFixture.getUsers()).thenReturn(mockUsers);

        //Act
        List<UserResponseDTO> actual = sut.getUsers();

        //Assert
        ObjectAssertions.assertEquals(usersResponseDTO, actual);
    }
}
