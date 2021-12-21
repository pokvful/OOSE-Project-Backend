package nl.han.aim.oosevt.lamport.controllers.dto.interventions.question;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

public class UpdateDTOValidationTest {
    @ParameterizedTest()
    @MethodSource
    public void updateQuestionInvalidDTOThrowsException(String name, String question) {
        //Arrange
        final UpdateQuestionRequestDTO updateQuestionRequestDTO = new UpdateQuestionRequestDTO(1, name, question, new ArrayList<>());
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, updateQuestionRequestDTO::validate);
    }

    @Test
    public void updateQuestionValidDTOPasses() {
        //Arrange
        final UpdateQuestionRequestDTO updateQuestionRequestDTO = new UpdateQuestionRequestDTO(1, "test", "test", new ArrayList<>());

        //Assert
        Assertions.assertDoesNotThrow(updateQuestionRequestDTO::validate);
    }

    private static Stream<Arguments> updateQuestionInvalidDTOThrowsException() {
        return Stream.of(
                Arguments.arguments("", "test"),
                Arguments.arguments("test", "")
        );
    }

}
