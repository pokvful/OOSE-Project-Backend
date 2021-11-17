package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    private CreateLocationRequestDTO createLocationRequestDTO;
    private UpdateLocationRequestDTO updateLocationRequestDTO;

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

        // instantiate SUT
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
    public void testUpdateLocationVerifies() {
        // Act
        sut.updateLocation(updateLocationRequestDTO);

        // Assert
        Mockito.verify(updateLocationRequestDTO).validate();
    }

    @Test
    public void testUpdateLocationCallsDB() {
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
}
