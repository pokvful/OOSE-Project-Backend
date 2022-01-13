package nl.han.aim.oosevt.lamport.data.dao;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.data.dao.goal.GoalDataMapper;
import nl.han.aim.oosevt.lamport.data.dao.goal.GoalDataMapperImpl;
import nl.han.aim.oosevt.lamport.data.entity.Goal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GoalDataMapperTests {

    private final GoalDataMapper sut = new GoalDataMapperImpl();

    @Test
    void roleMapperMapsDataCorrectly() throws SQLException {
        //Arrange
        final int goalId = 1;
        final String goalName = "Test";

        final ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("goal_id")).thenReturn(goalId);
        Mockito.when(resultSet.getString("goal_name")).thenReturn(goalName);

        final Goal expected = new Goal(goalId, goalName, new ArrayList<>());

        //Act
        final Goal actual =  sut.getFromResultSet(resultSet);

        //Assert
        ObjectAssertions.assertEquals(expected, actual);
    }
}
