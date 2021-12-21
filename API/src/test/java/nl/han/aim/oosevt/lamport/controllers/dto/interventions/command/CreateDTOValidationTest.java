package nl.han.aim.oosevt.lamport.controllers.dto.interventions.command;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CreateDTOValidationTest {
    @ParameterizedTest
    @MethodSource
    void createCommandInvalidDTOThrowsException(String name, String command) {
        //Arrange
        final CreateCommandRequestDTO createCommandRequestDTO = new CreateCommandRequestDTO(name, command);
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createCommandRequestDTO::validate);
    }

    @Test
    void createCommandValidDTOPasses() {
        //Arrange
        final CreateCommandRequestDTO createCommandRequestDTO = new CreateCommandRequestDTO("test", "test");

        //Assert
        Assertions.assertDoesNotThrow(createCommandRequestDTO::validate);
    }
    private static Stream<Arguments> createCommandInvalidDTOThrowsException() {
        return Stream.of(
                Arguments.arguments("", "test"),
                Arguments.arguments("test", "")
        );
    }
}
