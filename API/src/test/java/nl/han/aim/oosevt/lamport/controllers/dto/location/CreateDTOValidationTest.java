package nl.han.aim.oosevt.lamport.controllers.dto.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CreateDTOValidationTest {

    @ParameterizedTest()
    @MethodSource
    public void invalidDTOsThrowException(String name, int delay, double longitude, double latitude, int radius, int areaId, int franchiseId) {
        final CreateLocationRequestDTO createLocationRequestDTO = new CreateLocationRequestDTO(name, delay, longitude, latitude, radius, areaId, franchiseId, new ArrayList<>());

        Assertions.assertThrows(InvalidDTOException.class, createLocationRequestDTO::validate);
    }

    @Test
    public void validDTOPasses() {
        final CreateLocationRequestDTO createLocationRequestDTO = new CreateLocationRequestDTO("Subway", 1, 1,1, 1, 1, 1, new ArrayList<>());

        Assertions.assertDoesNotThrow(createLocationRequestDTO::validate);
    }

    private static Stream<Arguments> invalidDTOsThrowException() {
        return Stream.of(
                Arguments.arguments("", 0, 0, 0, 0, 0, 0),
                Arguments.arguments("", 1, 1, 1, 1, 1, 1),
                Arguments.arguments("Test", 0, 1, 1, 1, 1, 1),
                Arguments.arguments("Test", -181, 1, 1, 1, 0, 1),
                Arguments.arguments("Test", 181, 1, 0, 1, 1, 1),
                Arguments.arguments("Test", 1, 1, 0, 0, 1, 1),
                Arguments.arguments("Test", 1, -91, 1, 1, 0, 1),
                Arguments.arguments("Test", 1, 91, 1, 1, 0, 1),
                Arguments.arguments("Test", 1, 0, 1, 1, 1, 1),
                Arguments.arguments("Test", 1, 1, 1, 1, 1, 0)
        );
    }
}
