package nl.han.aim.oosevt.lamport.controllers.dto.interventions.questionaire;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionnaireRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CreateDTOValidationTest {
    @Test
    void createQuestionnaireInvalidDTOThrowsException() {
        //Arrange
        final CreateQuestionnaireRequestDTO createQuestionnaireRequestDTO = new CreateQuestionnaireRequestDTO("", new ArrayList<>());

        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createQuestionnaireRequestDTO::validate);
    }

    @Test
    void createQuestionnaireValidDTOPasses() {
        //Arrange
        final CreateQuestionnaireRequestDTO createQuestionnaireRequestDTO = new CreateQuestionnaireRequestDTO("test", new ArrayList<>());

        //Assert
        Assertions.assertDoesNotThrow(createQuestionnaireRequestDTO::validate);
    }
}
