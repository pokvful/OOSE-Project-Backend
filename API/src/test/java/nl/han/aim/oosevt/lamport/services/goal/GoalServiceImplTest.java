package nl.han.aim.oosevt.lamport.services.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.CreateGoalRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.goal.GoalDAO;
import nl.han.aim.oosevt.lamport.data.entity.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GoalServiceImplTest {
    private final int id = 2;

    private final String name = "Ga lopen";

    private GoalDAO goalDAOFixture;

    private Goal goal;

    private GoalServiceImpl sut;

    private GoalResponseDTO goalResponseDTO;

    private CreateGoalRequestDTO createGoalRequestDTO;

    @BeforeEach
    public void setup() {
        // Arrange create DTO
        createGoalRequestDTO = Mockito.spy(
                new CreateGoalRequestDTO(name));

        goalDAOFixture = Mockito.mock(GoalDAO.class);

        goal = new Goal(id, name);

        goalResponseDTO = new GoalResponseDTO(id, name);

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
}
