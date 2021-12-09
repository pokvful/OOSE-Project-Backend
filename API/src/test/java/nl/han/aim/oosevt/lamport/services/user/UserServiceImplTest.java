package nl.han.aim.oosevt.lamport.services.user;

import java.util.ArrayList;
import java.util.List;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UpdateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.CreateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDAO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.shared.HashProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class UserServiceImplTest {

    private final int userId = 1;
    private final String username = "Bart";
    private final int roleId = 1;
    private final String roleName = "Beheerder";
    private final String email = "b.barends@student.han.nl";
    private String password = "SomePassword";
    private String hash = "SomeHash"; 

    private Role mockRole;
    private User mockUser;
    private UserResponseDTO mockUserResponseDTO;
    private RoleResponseDTO mockRoleResponseDTO;
    private CreateUserRequestDTO mockCreateUserRequestDTO;
    private ArrayList<UserResponseDTO> usersResponseDTO;
    private ArrayList<User> mockUsers;
    private UpdateUserRequestDTO updateUserRequestDTO;

    private UserDAO userDAOFixture;
    private RoleDAO roleDAOFixture;
    private HashProvider hashProviderFixture;

    private UserServiceImpl sut;

    @BeforeEach
    public void setup() {
        mockRole = new Role(roleId, roleName);
        mockUser = new User(userId, username, email, password, mockRole);

        userDAOFixture = Mockito.mock(UserDAO.class);
        roleDAOFixture = Mockito.mock(RoleDAO.class);

        updateUserRequestDTO = new UpdateUserRequestDTO(userId, username, email, password, roleId);
        hashProviderFixture = Mockito.mock(HashProvider.class);

        mockCreateUserRequestDTO = Mockito.spy(
                new CreateUserRequestDTO(username, email, password, roleId));

        mockRoleResponseDTO = new RoleResponseDTO(roleId, roleName);
        mockUserResponseDTO =
                new UserResponseDTO(userId, username, email, mockRoleResponseDTO);
        mockUsers = new ArrayList<>();
        mockUsers.add(mockUser);

        usersResponseDTO = new ArrayList<>();
        usersResponseDTO.add(mockUserResponseDTO);

        sut = new UserServiceImpl(userDAOFixture, roleDAOFixture, hashProviderFixture);
    }

    @Test
    void updateUserHappy() {
        //Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(mockUser);
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);

        //Act
        sut.updateUser(updateUserRequestDTO);

        //Assert
        Mockito.verify(userDAOFixture).updateUser(eq(userId), eq(username), eq(email), any(String.class), eq(roleId));
    }

    @Test
    void updateUserHashes() {
        //Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(mockUser);
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);

        //Act
        sut.updateUser(updateUserRequestDTO);

        //Assert
        Mockito.verify(userDAOFixture).updateUser(eq(userId), eq(username), eq(email), not(eq(password)), eq(roleId));
    }

    @Test
    void updateUserUserNotFound() {
        //Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(null);
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(mockRole);

        //Act
        Executable act = () -> sut.updateUser(updateUserRequestDTO);

        //Assert
        Assertions.assertThrows(NotFoundException.class, act);
    }

    @Test
    void updateUserRoleNotFound() {
        //Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(mockUser);
        Mockito.when(roleDAOFixture.getRoleById(roleId)).thenReturn(null);

        //Act
        Executable act = () -> sut.updateUser(updateUserRequestDTO);

        //Assert
        Assertions.assertThrows(NotFoundException.class, act);
        sut = new UserServiceImpl(userDAOFixture, roleDAOFixture, hashProviderFixture);
    }

    @Test
    void getUserCallsDAO() {
        // Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(mockUser);

        // Act
        sut.getUserById(userId);

        // Assert
        Mockito.verify(userDAOFixture).getUserById(userId);
    }

    @Test
    void getExistingUser() {
        // Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(mockUser);

        // Act
        UserResponseDTO actual = sut.getUserById(userId);

        // Assert
        ObjectAssertions.assertEquals(mockUserResponseDTO, actual);
    }

    @Test
    void getNoUser() {
        // Arrange
        Mockito.when(userDAOFixture.getUserById(userId)).thenReturn(null);

        // Assert
        Assertions.assertThrows(NotFoundException.class,
                () -> sut.getUserById(userId));
    }

    @Test
    void getUsersCallsDAO() {
        // Act
        sut.getUsers();

        // Assert
        Mockito.verify(userDAOFixture).getUsers();
    }

    @Test
    void getExistingUsers() {
        // Arrange
        Mockito.when(userDAOFixture.getUsers()).thenReturn(mockUsers);

        // Act
        List<UserResponseDTO> actual = sut.getUsers();

        // Assert
        ObjectAssertions.assertEquals(usersResponseDTO, actual);
    }

    @Test
    void getNoUsers() {
        // Arrange
        usersResponseDTO.clear();
        mockUsers.clear();

        Mockito.when(userDAOFixture.getUsers()).thenReturn(mockUsers);

        // Act
        List<UserResponseDTO> actual = sut.getUsers();

        // Assert
        ObjectAssertions.assertEquals(usersResponseDTO, actual);
    }

    @Test
    public void testCreateUserVerifies() {
        // Arrange
        Mockito.when(userDAOFixture.getUserById(mockUser.getUserId()))
                .thenReturn(mockUser);

        Mockito.when(hashProviderFixture.hash(password)).thenReturn(hash);

        // Act
        sut.createUser(mockCreateUserRequestDTO);

        // Assert
        Mockito.verify(mockCreateUserRequestDTO).validate();
    }

    @Test
    public void testCreateUserCallsDB() {
        // Arrange
        Mockito.when(userDAOFixture.getUserById(mockUser.getUserId()))
                .thenReturn(mockUser);
        Mockito.when(hashProviderFixture.hash(password)).thenReturn(hash);

        // Act
        sut.createUser(mockCreateUserRequestDTO);

        // Assert
        Mockito.verify(userDAOFixture)
                .createUser(mockUser.getUsername(), mockUser.getEmail(), hashProviderFixture.hash(mockUser.getPassword()),
                        mockUser.getRole().getRoleId());
    }
}
