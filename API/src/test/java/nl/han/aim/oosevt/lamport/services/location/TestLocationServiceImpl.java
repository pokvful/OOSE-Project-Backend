package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class TestLocationServiceImpl {
    private final String name = "mcDonalds";
    private final int delay = 10;
    private final double longitude = 4.32132;
    private final double latitude = 51.32132431;
    private final int radius = 30;
    private final int areaId = 1;

    private LocationServiceImpl sut;
    private LocationDAO locationDAOFixture;
    private CreateLocationRequestDTO createLocationRequestDTO;

    @BeforeEach
    public void setup() {
        CreateLocationRequestDTO realCreateLocationRequestDTO = new CreateLocationRequestDTO();
        createLocationRequestDTO = Mockito.spy(realCreateLocationRequestDTO);

        createLocationRequestDTO.setName(name);
        createLocationRequestDTO.setDelay(delay);
        createLocationRequestDTO.setLongitude(longitude);
        createLocationRequestDTO.setLatitude(latitude);
        createLocationRequestDTO.setRadius(radius);
        createLocationRequestDTO.setAreaId(areaId);

        locationDAOFixture = Mockito.mock(LocationDAO.class);

        sut = new LocationServiceImpl(locationDAOFixture);
    }

    @Test
    public void testCreateLocationVerifies() {
        // Act
        sut.createLocation(createLocationRequestDTO);

        // Assert
        Mockito.verify(createLocationRequestDTO).validate();
    }

    @Test
    public void testCreateLocationCallsDB() {
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
        Mockito.when(locationDAOFixture.getLocationById(Mockito.anyInt())).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> sut.deleteLocation(0));
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
    void getNoLocations() {

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
        var expected = 3;
        Mockito.when(this.locationDAOFixture.getLocations()).thenReturn(new ArrayList<>() {{
            add(new Location());
            add(new Location());
            add(new Location());
        }});

        //Act
        var actual = this.sut.getLocations().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getLocationCallsDAO() {

        //Arrange
        Mockito.when(this.locationDAOFixture.getLocationById(Mockito.anyInt())).thenReturn(new Location());

        //Act
        this.sut.getLocation(0);

        //Assert
        Mockito.verify(this.locationDAOFixture).getLocationById(Mockito.anyInt());
    }

    @Test
    void getExistingLocation() {

        //Arrange
        Mockito.when(this.locationDAOFixture.getLocationById(Mockito.anyInt())).thenReturn(new Location());
        var expected = new LocationResponseDTO();

        //Act
        var actual = this.sut.getLocation(0);

        //Assert
        Assertions.assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void getNoLocation() {

        //Arrange
        Mockito.when(this.locationDAOFixture.getLocationById(Mockito.anyInt())).thenReturn(null);

        //Assert
        Assertions.assertThrows(NotFoundException.class, (() -> this.sut.getLocation(0)));
    }
}
