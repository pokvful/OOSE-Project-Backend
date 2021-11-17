package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocationServiceImplTest {
    private final int id = 2;
    private final String name = "mcDonalds";
    private final int delay = 10;
    private final double longitude = 4.32132;
    private final double latitude = 51.32132431;
    private final int radius = 30;
    private final int areaId = 1;

    private LocationServiceImpl sut;
    private LocationDAO locationDAOFixture;
    private AreaDAO areaDAOFixture;
    private CreateLocationRequestDTO createLocationRequestDTO;
    private UpdateLocationRequestDTO updateLocationRequestDTO;
    private Location mockLocation;
    private Area mockArea;

    @BeforeEach
    public void setup() {
        // arrange create DTO
        createLocationRequestDTO = Mockito.spy(
                new CreateLocationRequestDTO(name, delay, longitude, latitude, radius, areaId)
        );

        updateLocationRequestDTO = Mockito.spy(
                new UpdateLocationRequestDTO(id, name, delay, longitude, latitude, radius, areaId)
        );

        locationDAOFixture = Mockito.mock(LocationDAO.class);
        areaDAOFixture = Mockito.mock(AreaDAO.class);

        mockLocation = new Location();
        mockArea = new Area();

        // instantiate SUT
        sut = new LocationServiceImpl(locationDAOFixture, areaDAOFixture);
    }

    @Test
    public void testCreateChecksAreaExists() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(null);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act/Assert
        assertThrows(NotFoundException.class, () -> sut.createLocation(createLocationRequestDTO));
    }

    @Test
    public void testCreateLocationVerifies() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act
        sut.createLocation(createLocationRequestDTO);

        // Assert
        Mockito.verify(createLocationRequestDTO).validate();
    }

    @Test
    public void testCreateLocationCallsDB() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act
        sut.createLocation(createLocationRequestDTO);

        // Assert
        Mockito.verify(locationDAOFixture).createLocation(
                name,
                delay,
                longitude,
                latitude,
                radius,
                areaId
        );
    }

    @Test
    public void testUpdateChecksAreaExists() {
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> sut.updateLocation(updateLocationRequestDTO));
    }

    @Test
    public void testUpdateChecksLocationExists() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(null);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act/Assert
        assertThrows(NotFoundException.class, () -> sut.updateLocation(updateLocationRequestDTO));
    }

    @Test
    public void testUpdateLocationVerifies() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act
        sut.updateLocation(updateLocationRequestDTO);

        // Assert
        Mockito.verify(updateLocationRequestDTO).validate();
    }

    @Test
    public void testUpdateLocationCallsDB() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act
        sut.updateLocation(updateLocationRequestDTO);

        // Assert
        Mockito.verify(locationDAOFixture).updateLocation(
                id,
                name,
                delay,
                longitude,
                latitude,
                radius,
                areaId
        );
    }

    @Test
    public void testDeleteLocation() {
        // Arrange
        Mockito.when(locationDAOFixture.getLocationById(Mockito.anyInt())).thenReturn(new Location());
        Mockito.doNothing().when(this.locationDAOFixture).deleteLocation(Mockito.anyInt());

        // Act
        sut.deleteLocation(1);

        // Assert
        Mockito.verify(locationDAOFixture).deleteLocation(1);
    }

    @Test
    public void testDeleteLocationThrowsException() {
        assertThrows(NotFoundException.class, () -> sut.deleteLocation(0));
    }

    @Test
    public void testGetLocations() {
        //Arrange
        Mockito.when(this.locationDAOFixture.getLocations()).thenReturn(new ArrayList<>());

        //Act
        sut.getLocations();

        //Assert
        Mockito.verify(this.locationDAOFixture).getLocations();
    }

    @Test
    void getNoLocation() {

        //Arrange
        var expected = 0;
        Mockito.when(this.locationDAOFixture.getLocations()).thenReturn(new ArrayList<>());

        //Act
        int actual = sut.getLocations().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getExistingLocations() {

        //Arrange
        int expected = 3;

        Mockito.when(this.locationDAOFixture.getLocations()).thenReturn((ArrayList<Location>) List.of(
                new Location(),
                new Location(),
                new Location()
        ));

        //Act
        int actual = sut.getLocations().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
