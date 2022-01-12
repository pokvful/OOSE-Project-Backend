package nl.han.aim.oosevt.lamport.data.dao;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDataMapper;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDataMapperImpl;
import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FranchiseDataMapperTests {

    private final FranchiseDataMapper sut = new FranchiseDataMapperImpl();

    @Test
    void roleMapperMapsDataCorrectly() throws SQLException {
        //Arrange
        final int franchiseId = 1;
        final String franchiseName = "Test";

        final ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("franchise_id")).thenReturn(franchiseId);
        Mockito.when(resultSet.getString("franchise_name")).thenReturn(franchiseName);

        final Franchise expected = new Franchise(franchiseId, franchiseName);

        //Act
        final Franchise actual =  sut.getFromResultSet(resultSet);

        //Assert
        ObjectAssertions.assertEquals(expected, actual);
    }
}
