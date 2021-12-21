package nl.han.aim.oosevt.lamport.controllers.dto.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.CreateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateDTOValidationTest {
    @Test
    void createFranchiseInvalidDTOThrowsException() {
        //Arrange
        final CreateFranchiseRequestDTO createFranchiseRequestDTO = new CreateFranchiseRequestDTO("");
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createFranchiseRequestDTO::validate);
    }

    @Test
    void createFranchiseValidDTOPasses() {
        //Arrange
        final CreateFranchiseRequestDTO createFranchiseRequestDTO = new CreateFranchiseRequestDTO("test");

        //Assert
        Assertions.assertDoesNotThrow(createFranchiseRequestDTO::validate);
    }

}
