package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Intervention;
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
    private final String areaName = "Nijmegen";
    private final double areaLongitude = 5.312312;
    private final double areaLatitude = 40.432432;
    private final int areaRadius = 4000;

    private final int interventionIdA = 1;
    private final String interventionNameA = "saladebar";
    private final int interventionIdB = 2;
    private final String interventionNameB = "hardlopen";
    private final int interventionIdC = 3;
    private final String interventionNameC = "kerken tellen";

    private LocationDAO locationDAOFixture;
    private AreaDAO areaDAOFixture;
    private InterventionDAO interventionDAOFixture;
    private CreateLocationRequestDTO createLocationRequestDTO;
    private UpdateLocationRequestDTO updateLocationRequestDTO;
    private Location mockLocation;
    private Area mockArea;
    private List<Intervention> linkedInterventions;
    private Intervention interventionA;
    private Intervention interventionB;
    private Intervention interventionC;
    private List<Integer> linkedInterventionIds;

    private LocationServiceImpl sut;

    @BeforeEach
    public void setup() {
        linkedInterventionIds = new ArrayList<>();
        linkedInterventionIds.add(interventionIdA);
        linkedInterventionIds.add(interventionIdB);
        linkedInterventionIds.add(interventionIdC);

        // arrange create DTO
        createLocationRequestDTO = Mockito.spy(
                new CreateLocationRequestDTO(name, delay, longitude, latitude, radius, areaId, linkedInterventionIds)
        );

        updateLocationRequestDTO = Mockito.spy(
                new UpdateLocationRequestDTO(id, name, delay, longitude, latitude, radius, areaId, linkedInterventionIds)
        );

        interventionA = new Intervention(interventionIdA, interventionNameA);
        interventionB = new Intervention(interventionIdB, interventionNameB);
        interventionC = new Intervention(interventionIdC, interventionNameC);

        linkedInterventions = new ArrayList<>();

        linkedInterventions.add(interventionA);
        linkedInterventions.add(interventionB);
        linkedInterventions.add(interventionC);

        locationDAOFixture = Mockito.mock(LocationDAO.class);
        areaDAOFixture = Mockito.mock(AreaDAO.class);
        interventionDAOFixture = Mockito.mock(InterventionDAO.class);

        mockLocation = new Location(id, name, delay, longitude, latitude, radius, mockArea, linkedInterventions);
        mockArea = new Area(areaId, areaName, areaLongitude, areaLatitude, areaRadius);

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

        // Act
        sut.createLocation(createLocationRequestDTO);

        // Assert
        Mockito.verify(locationDAOFixture).createLocation(
                name,
                delay,
                longitude,
                latitude,
                radius,
                areaId,
                linkedInterventionIds
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
                areaId,
                linkedInterventionIds
        );
    }

    @Test
    public void testDeleteLocation() {
        // Arrange
        Mockito.when(locationDAOFixture.getLocationById(Mockito.anyInt())).thenReturn(new Location(id, name, delay, longitude, latitude, radius, mockArea, linkedInterventions));
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

        Mockito.when(this.locationDAOFixture.getLocations()).thenReturn(new ArrayList<>() {{
            add(new Location(id, name, delay, longitude, latitude, radius, mockArea, linkedInterventions));
            add(new Location(id, name, delay, longitude, latitude, radius, mockArea, linkedInterventions));
            add(new Location(id, name, delay, longitude, latitude, radius, mockArea, linkedInterventions));
        }});

        //Act
        int actual = sut.getLocations().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
