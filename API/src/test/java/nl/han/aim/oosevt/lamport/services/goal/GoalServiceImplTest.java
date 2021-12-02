package nl.han.aim.oosevt.lamport.services.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.UpdateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.goal.GoalDAO;
import nl.han.aim.oosevt.lamport.data.entity.Goal;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

public class GoalServiceImplTest {
    private final int id = 2;
    private final String name = "Ga lopen";

    private GoalDAO goalDAOFixture;
    private Goal goal;
    private GoalServiceImpl sut;
    private GoalResponseDTO goalResponseDTO;
    private UpdateGoalRequestDTO updateGoalRequestDTO;
    private CreateGoalRequestDTO createGoalRequestDTO;

    @BeforeEach
    public void setup() {
        // Arrange create DTO
        createGoalRequestDTO = Mockito.spy(
                new CreateGoalRequestDTO(name));

        goalDAOFixture = Mockito.mock(GoalDAO.class);

        goal = new Goal(id, name);

        goalResponseDTO = new GoalResponseDTO(id, name);

        updateGoalRequestDTO = new UpdateGoalRequestDTO(name, id);
        // instantiate SUT
        sut = new GoalServiceImpl(goalDAOFixture);
    }

    @Test
    public void testCreateGoalValidates() {
        // Arrange
        Mockito.when(goalDAOFixture.getGoalById(id)).thenReturn(goal);

        // Act
        sut.createGoal(createGoalRequestDTO);

        // Assert
        Mockito.verify(createGoalRequestDTO).validate();
    }

    @Test
    public void testCreateGoalCallsDB() {
        // Arrange
        Mockito.when(goalDAOFixture.getGoalById(id)).thenReturn(goal);

        // Act
        sut.createGoal(createGoalRequestDTO);

        // Assert
        Mockito.verify(goalDAOFixture).createGoal(
                name
        );
    }

    @Test
    public void updateGoalHappy() {
        // Arrange
        Mockito.when(goalDAOFixture.getGoalById(id)).thenReturn(goal);

        // Act
        sut.updateGoal(updateGoalRequestDTO);

        // Assert
        Mockito.verify(goalDAOFixture).updateGoal(
                id,
                name
        );
    }

    @Test
    public void updateGoalGoalBestaatNiet() {
        // Arrange
        Mockito.when(goalDAOFixture.getGoalById(id)).thenReturn(null);

        // Act
        Executable executable = () -> sut.updateGoal(updateGoalRequestDTO);

        // Assert
        Assertions.assertThrows(NotFoundException.class, executable);
    }
}
