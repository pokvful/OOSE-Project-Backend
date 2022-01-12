package nl.han.aim.oosevt.lamport.data.dao;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDataMapper;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDataMapperImpl;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationDataMapperTests {
    private final LocationDataMapper sut = new LocationDataMapperImpl();

    @Test
    void locationMapperMapsDataCorrectly() throws SQLException {
        //Arrange
        final int locationId = 1;
        final String locationName = "TestLocation";
        final int delay = 100;
        final double longitude = 10;
        final double latitude = 15;
        final int radius = 1000;
        final int areaId = 1;
        final String areaName = "Test";
        final double areaLongitude = 10;
        final double areaLatitude = 15;
        final int areaRadius = 100;

        final Area expectedArea = new Area(areaId, areaName, areaLongitude, areaLatitude, areaRadius);

        final ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("area_id")).thenReturn(areaId);
        Mockito.when(resultSet.getString("area_name")).thenReturn(areaName);
        Mockito.when(resultSet.getDouble("area_longitude")).thenReturn(areaLongitude);
        Mockito.when(resultSet.getDouble("area_latitude")).thenReturn(areaLatitude);
        Mockito.when(resultSet.getInt("area_radius")).thenReturn(areaRadius);

        Mockito.when(resultSet.getInt("location_id")).thenReturn(locationId);
        Mockito.when(resultSet.getString("location_name")).thenReturn(locationName);
        Mockito.when(resultSet.getInt("delay")).thenReturn(delay);
        Mockito.when(resultSet.getDouble("longitude")).thenReturn(longitude);
        Mockito.when(resultSet.getDouble("latitude")).thenReturn(latitude);
        Mockito.when(resultSet.getInt("radius")).thenReturn(radius);

        Mockito.when(resultSet.getInt("franchise_id")).thenReturn(0);
        Mockito.when(resultSet.getString("franchise_name")).thenReturn(null);

        final Location expected = new Location(locationId, locationName, delay, longitude, latitude, radius, expectedArea, new Franchise(0, null), new ArrayList<>());

        //Act
        final Location actual =  sut.getFromResultSet(resultSet);

        //Assert
        ObjectAssertions.assertEquals(expected, actual);
    }
}
