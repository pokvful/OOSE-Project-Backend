package nl.han.aim.oosevt.lamport.controllers.dto.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.UpdateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateDTOValidationTest {
    @Test
    void updateGoalInvalidDTOThrowsException() {
        //Arrange
        final UpdateGoalRequestDTO updateGoalRequestDTO = new UpdateGoalRequestDTO("", 1);
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, updateGoalRequestDTO::validate);
    }

    @Test
    void updateGoalValidDTOPasses() {
        //Arrange
        final UpdateGoalRequestDTO updateGoalRequestDTO = new UpdateGoalRequestDTO("test", 1);

        //Assert
        Assertions.assertDoesNotThrow(updateGoalRequestDTO::validate);
    }

}
