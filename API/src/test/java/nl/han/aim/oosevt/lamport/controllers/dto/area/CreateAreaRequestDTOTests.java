package nl.han.aim.oosevt.lamport.controllers.dto.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CreateAreaRequestDTOTests {

    @ParameterizedTest()
    @MethodSource
    public void invalidDTOsThrowException(String name, double longitude, double latitude, int radius) {
        final CreateAreaRequestDTO createAreaRequestDTO = new CreateAreaRequestDTO(name, longitude, latitude, radius);

        Assertions.assertThrows(InvalidDTOException.class, createAreaRequestDTO::validate);
    }

    @Test
    public void validDTOPasses() {
        final CreateAreaRequestDTO createAreaRequestDTO = new CreateAreaRequestDTO("Test", 1, 1, 1);

        Assertions.assertDoesNotThrow(createAreaRequestDTO::validate);

    }

    private static Stream<Arguments> invalidDTOsThrowException() {
        return Stream.of(
                Arguments.arguments("", 0, 0, 0),
                Arguments.arguments("", 1, 1, 1),
                Arguments.arguments("Test", 0, 1, 1),
                Arguments.arguments("Test", -181, 1, 1),
                Arguments.arguments("Test", 181, 1, 1),
                Arguments.arguments("Test", 1, 1, 0),
                Arguments.arguments("Test", 1, -91, 1),
                Arguments.arguments("Test", 1, 91, 1),
                Arguments.arguments("Test", 1, 0, 1)
        );
    }
}
