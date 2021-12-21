package nl.han.aim.oosevt.lamport.controllers.dto.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.UpdateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateDTOValidationTest {
    @Test
    void updateFranchiseInvalidDTOThrowsException() {
        //Arrange
        final UpdateFranchiseRequestDTO updateFranchiseRequestDTO = new UpdateFranchiseRequestDTO(1, "");
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, updateFranchiseRequestDTO::validate);
    }

    @Test
    void createFranchiseValidDTOPasses() {
        //Arrange
        final UpdateFranchiseRequestDTO updateFranchiseRequestDTO = new UpdateFranchiseRequestDTO(1, "test");

        //Assert
        Assertions.assertDoesNotThrow(updateFranchiseRequestDTO::validate);
    }

}
