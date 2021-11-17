package nl.han.aim.oosevt.lamport.controllers.dto.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
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
    public void invalidDTOsThrowException(int id, String name, int delay, double longitude, double latitude, int radius, int areaId) {
        final UpdateLocationRequestDTO createLocationRequestDTO = new UpdateLocationRequestDTO(id, name, delay, longitude, latitude, radius, areaId);

        Assertions.assertThrows(InvalidDTOException.class, createLocationRequestDTO::validate);
    }

    @Test
    public void validDTOPasses() {
        final UpdateLocationRequestDTO createLocationRequestDTO = new UpdateLocationRequestDTO(1, "Subway", 1, 1,1, 1, 1);

        Assertions.assertDoesNotThrow(createLocationRequestDTO::validate);
    }

    private static Stream<Arguments> invalidDTOsThrowException() {
        return Stream.of(
                Arguments.arguments(0, "", 0, 0, 0, 0, 0),
                Arguments.arguments(1, "", 1, 1, 1, 1, 1),
                Arguments.arguments(1, "Test", 0, 1, 1, 1, 1),
                Arguments.arguments(1, "Test", -181, 1, 1, 1, 0),
                Arguments.arguments(1, "Test", 181, 1, 0, 1, 1),
                Arguments.arguments(1, "Test", 1, 1, 0, 0, 1),
                Arguments.arguments(1, "Test", 1, -91, 1, 1, 0),
                Arguments.arguments(1, "Test", 1, 91, 1, 1, 0),
                Arguments.arguments(1, "Test", 1, 0, 1, 1, 1)
        );
    }
}
