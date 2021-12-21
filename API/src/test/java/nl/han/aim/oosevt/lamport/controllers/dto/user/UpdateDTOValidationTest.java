package nl.han.aim.oosevt.lamport.controllers.dto.user;

import nl.han.aim.oosevt.lamport.controllers.user.dto.UpdateUserRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class UpdateDTOValidationTest {

    @ParameterizedTest()
    @MethodSource
    public void invalidDTOsThrowException(String userName, String email, String password, int roleId) {
        final UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO(1, userName, email, password, roleId);

        Assertions.assertThrows(InvalidDTOException.class, updateUserRequestDTO::validate);
    }

    @Test
    public void validDTOPasses() {
        final UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO(1, "username", "email", "password", 1);

        Assertions.assertDoesNotThrow(updateUserRequestDTO::validate);
    }

    private static Stream<Arguments> invalidDTOsThrowException() {
        return Stream.of(
                Arguments.arguments("", "test@email.com", "password", 1),
                Arguments.arguments("username", "", "password", 1),
                Arguments.arguments("username", "test@email.com", "", 1),
                Arguments.arguments("username", "test@email.com", "password", 0),
                Arguments.arguments("", "", "", 0)
        );
    }
}
