package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}
