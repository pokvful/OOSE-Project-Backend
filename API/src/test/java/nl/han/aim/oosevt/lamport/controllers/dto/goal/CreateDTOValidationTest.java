package nl.han.aim.oosevt.lamport.controllers.dto.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.ProfileQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateDTOValidationTest {
    List<ProfileQuestionRequestDTO> profileQuestionsEmpty = new ArrayList<>();
    List<ProfileQuestionRequestDTO> profileQuestionsNotEmpty = new ArrayList<>();

    @Test
    void createGoalInvalidDTOThrowsException() {
        //Arrange
        final CreateGoalRequestDTO createGoalRequestDTO = new CreateGoalRequestDTO("", profileQuestionsEmpty);
        //Assert
        Assertions.assertThrows(InvalidDTOException.class, createGoalRequestDTO::validate);
    }

    @Test
    void createGoalValidDTOPasses() {
        profileQuestionsNotEmpty.add(new ProfileQuestionRequestDTO("Name"));

        //Arrange
        final CreateGoalRequestDTO createGoalRequestDTO = new CreateGoalRequestDTO("test", profileQuestionsNotEmpty);

        //Assert
        Assertions.assertDoesNotThrow(createGoalRequestDTO::validate);
    }
}
