package nl.han.aim.oosevt.lamport.controllers.dto.role;

import nl.han.aim.oosevt.lamport.controllers.role.dto.UpdateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UpdateDTOValidationTest {
    @Test
    void updateRoleInvalidDTOThrowsException() {
        //Arrange
        final UpdateRoleRequestDTO updateRoleRequestDTO = new UpdateRoleRequestDTO(1, new ArrayList<>(), "");

        //Assert
        Assertions.assertThrows(InvalidDTOException.class, updateRoleRequestDTO::validate);
    }

    @Test
    void updateRoleValidDTOPasses() {
        //Arrange
        final UpdateRoleRequestDTO updateRoleRequestDTO = new UpdateRoleRequestDTO(1, new ArrayList<>(), "test");

        //Assert
        Assertions.assertDoesNotThrow(updateRoleRequestDTO::validate);
    }

}
