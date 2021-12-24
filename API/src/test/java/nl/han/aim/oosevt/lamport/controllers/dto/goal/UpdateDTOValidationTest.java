package nl.han.aim.oosevt.lamport.controllers.dto.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.ProfileQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.UpdateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UpdateDTOValidationTest {
    List<ProfileQuestionRequestDTO> profileQuestionsEmpty = new ArrayList<>();
    List<ProfileQuestionRequestDTO> profileQuestionsNotEmpty = new ArrayList<>();

    @Test
    void updateGoalInvalidDTOThrowsException() {
        //Arrange
        final UpdateGoalRequestDTO updateGoalRequestDTO = new UpdateGoalRequestDTO("", profileQuestionsEmpty, 1);
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, updateGoalRequestDTO::validate);
    }

    @Test
    void updateGoalValidDTOPasses() {
        profileQuestionsNotEmpty.add(new ProfileQuestionRequestDTO("Test"));

        //Arrange
        final UpdateGoalRequestDTO updateGoalRequestDTO = new UpdateGoalRequestDTO("test", profileQuestionsNotEmpty, 1);

        //Assert
        Assertions.assertDoesNotThrow(updateGoalRequestDTO::validate);
    }

}
