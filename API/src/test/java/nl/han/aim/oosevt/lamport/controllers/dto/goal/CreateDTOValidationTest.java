package nl.han.aim.oosevt.lamport.controllers.dto.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateDTOValidationTest {
    @Test
    void createGoalInvalidDTOThrowsException() {
        //Arrange
        final CreateGoalRequestDTO createGoalRequestDTO = new CreateGoalRequestDTO("");
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createGoalRequestDTO::validate);
    }

    @Test
    void createGoalValidDTOPasses() {
        //Arrange
        final CreateGoalRequestDTO createGoalRequestDTO = new CreateGoalRequestDTO("test");

        //Assert
        Assertions.assertDoesNotThrow(createGoalRequestDTO::validate);
    }
}
