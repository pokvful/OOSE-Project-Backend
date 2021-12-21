package nl.han.aim.oosevt.lamport.controllers.dto.role;

import nl.han.aim.oosevt.lamport.controllers.role.dto.CreateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CreateDTOValidationTest {
    @Test
    void createRoleInvalidDTOThrowsException() {
        //Arrange
        final CreateRoleRequestDTO createRoleRequestDTO = new CreateRoleRequestDTO(new ArrayList<>(), "");

        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createRoleRequestDTO::validate);
    }

    @Test
    void createRoleValidDTOPasses() {
        //Arrange
        final CreateRoleRequestDTO createRoleRequestDTO = new CreateRoleRequestDTO(new ArrayList<>(), "test");

        //Assert
        Assertions.assertDoesNotThrow(createRoleRequestDTO::validate);
    }

}
