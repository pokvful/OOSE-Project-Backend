package nl.han.aim.oosevt.lamport.data.dao;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDataMapper;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDataMapperImpl;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaDataMapperTests {

    private final AreaDataMapper sut = new AreaDataMapperImpl();

    @Test
    void areaMapperMapsDataCorrectly() throws SQLException {
        //Arrange
        final int areaId = 1;
        final String areaName = "Test";
        final double longitude = 10;
        final double latitude = 15;
        final int radius = 100;

        final ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("area_id")).thenReturn(areaId);
        Mockito.when(resultSet.getString("area_name")).thenReturn(areaName);
        Mockito.when(resultSet.getDouble("longitude")).thenReturn(longitude);
        Mockito.when(resultSet.getDouble("latitude")).thenReturn(latitude);
        Mockito.when(resultSet.getInt("radius")).thenReturn(radius);

        final Area expected = new Area(areaId, areaName, longitude, latitude, radius);

        //Act
        final Area actual =  sut.getFromResultSet(resultSet);

        //Assert
        ObjectAssertions.assertEquals(expected, actual);
    }
}
