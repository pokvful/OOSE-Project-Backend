package nl.han.aim.oosevt.lamport.controllers.dto.interventions.questionaire;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

public class UpdateDTOValidationTest {
    @Test
    void updateQuestionnaireInvalidDTOThrowsException() {
        //Arrange
        final CreateQuestionnaireRequestDTO createQuestionnaireRequestDTO = new CreateQuestionnaireRequestDTO("", new ArrayList<>());

        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createQuestionnaireRequestDTO::validate);
    }

    @Test
    void updateQuestionnaireValidDTOPasses() {
        //Arrange
        final UpdateQuestionnaireRequestDTO updateQuestionnaireRequestDTO = new UpdateQuestionnaireRequestDTO("test", new ArrayList<>(), 1);

        //Assert
        Assertions.assertDoesNotThrow(updateQuestionnaireRequestDTO::validate);
    }

}
