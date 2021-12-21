package nl.han.aim.oosevt.lamport.controllers.dto.interventions.question;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
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
    public void createQuestionInvalidDTOThrowsException(String name, String question) {
        //Arrange
        final CreateQuestionRequestDTO createQuestionRequestDTO = new CreateQuestionRequestDTO(name, new ArrayList<>(), question);
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createQuestionRequestDTO::validate);
    }

    @Test
    public void createQuestionValidDTOPasses() {
        //Arrange
        final CreateQuestionRequestDTO updateQuestionRequestDTO = new CreateQuestionRequestDTO("test", new ArrayList<>(), "test");

        //Assert
        Assertions.assertDoesNotThrow(updateQuestionRequestDTO::validate);
    }

    private static Stream<Arguments> createQuestionInvalidDTOThrowsException() {
        return Stream.of(
                Arguments.arguments("", "test"),
                Arguments.arguments("test", "")
        );
    }
}
