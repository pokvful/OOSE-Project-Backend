package nl.han.aim.oosevt.lamport.services.auth;

import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.Goal;
import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import nl.han.aim.oosevt.lamport.exceptions.UnauthorizedException;
import nl.han.aim.oosevt.lamport.shared.HashProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class LoginServiceImplTest {

    private final String loginUsername = "Test";
    private final String loginPassword = "Test";
    private final String correctHash = "correctHash";

    private final LoginRequestDTO loginRequestDto = new LoginRequestDTO(loginUsername, loginPassword);
    private final Role role = new Role(1, "test", new ArrayList<>());
    private final Goal goal = new Goal(1, "test", new ArrayList<>());
    private final User loginUser = new User(1, loginUsername, "test@test.nl", correctHash, role, goal);
    private LoginServiceImpl sut;
    private UserDAO userDAOFixture;
    private HashProvider hashProviderFixture;

    @BeforeEach
    public void setup() {
        userDAOFixture = Mockito.mock(UserDAO.class);
        hashProviderFixture = Mockito.mock(HashProvider.class);

        sut = Mockito.spy(new LoginServiceImpl(userDAOFixture, hashProviderFixture));
    }

    @Test
    public void userNotFoundThrowsException() {
        // Arrange
        Mockito.when(userDAOFixture.getUserByUsername(loginUsername)).thenReturn(null);

        // Assert
        Assertions.assertThrows(InvalidDTOException.class, () -> sut.login(loginRequestDto));
    }

    @Test
    public void userInvalidPasswordThrowsException() {
        // Arrange
        Mockito.when(userDAOFixture.getUserByUsername(loginUsername)).thenReturn(loginUser);
        Mockito.when(hashProviderFixture.hash(loginPassword)).thenReturn("incorrectHash");

        // Assert
        Assertions.assertThrows(InvalidDTOException.class, () -> sut.login(loginRequestDto));
    }

    @Test
    public void loginLogsInWithCorrectData() {
        // Arrange
        Mockito.when(userDAOFixture.getUserByUsername(loginUsername)).thenReturn(loginUser);
        Mockito.when(hashProviderFixture.matches(loginPassword, correctHash)).thenReturn(true);
        Mockito.when(sut.getJWTSecret()).thenReturn("JWT_SECRET");

        // Assert
        sut.login(loginRequestDto);
    }
}
