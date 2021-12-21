package nl.han.aim.oosevt.lamport.controllers.dto.interventions.command;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class UpdateDTOValidationTest {
    @ParameterizedTest
    @MethodSource
    void updateCommandInvalidDTOThrowsException(String name, String command) {
        //Arrange
        final UpdateCommandRequestDTO updateCommandRequestDTO = new UpdateCommandRequestDTO(name, command, 1);
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, updateCommandRequestDTO::validate);
    }

    @Test
    void updateCommandValidDTOPasses() {
        //Arrange
        final UpdateCommandRequestDTO updateCommandRequestDTO = new UpdateCommandRequestDTO("test", "test", 1);

        //Assert
        Assertions.assertDoesNotThrow(updateCommandRequestDTO::validate);
    }

    private static Stream<Arguments> updateCommandInvalidDTOThrowsException() {
        return Stream.of(
                Arguments.arguments("", "test"),
                Arguments.arguments("test", "")
        );
    }

}
