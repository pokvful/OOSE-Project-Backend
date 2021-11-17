package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertThrows(NotFoundException.class, () -> sut.deleteLocation(0));
    }
}
