package nl.han.aim.oosevt.lamport.services.location;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.InterventionResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.LocationResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.*;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocationServiceImplTest {
    private final int id = 2;
    private final String name = "mcDonalds";
    private final int delay = 10;
    private final double longitude = 4.32132;
    private final double latitude = 51.32132431;
    private final int radius = 30;
    private final int areaId = 1;
    private final int franchiseId = 1;
    private final String franchiseName = "mcDonalds";
    private final String areaName = "Nijmegen";
    private final double areaLongitude = 5.312312;
    private final double areaLatitude = 40.432432;
    private final int areaRadius = 4000;

    private final int interventionIdA = 1;
    private final String interventionNameA = "saladebar";
    private final String commandA = "ga naar de saladebar";

    private final int interventionIdB = 2;
    private final String interventionNameB = "bijbelvertaler";
    private final String interventionQuestionB = "Wie is een bijbelvertaler";

    private final int interventionIdC = 3;
    private final String interventionNameC = "kerken tellen";

    private final String answerAAnswer = "Maarten Luther";
    private final String answerBAnswer = "Willibrord";
    private final String answerCAnswer = "Calvijn";

    private LocationDAO locationDAOFixture;
    private AreaDAO areaDAOFixture;
    private FranchiseDAO franchiseDAOFixture;
    private InterventionDAO interventionDAOFixture;
    private CreateLocationRequestDTO createLocationRequestDTO;
    private UpdateLocationRequestDTO updateLocationRequestDTO;
    private Location mockLocation;
    private Area mockArea;
    private Franchise mockFranchise;
    private List<Intervention> linkedInterventions;
    private Command interventionA;
    private Question interventionB;
    private Questionnaire interventionC;
    private List<Answer> answers;
    private List<Question> questions;
    private List<Integer> linkedInterventionIds;
    private LocationResponseDTO locationResponseDTO;
    private List<InterventionResponseDTO> interventionResponseDTOs;
    private AreaResponseDTO areaResponseDTO;
    private FranchiseResponseDTO franchiseResponseDTO;
    private Answer answerA;
    private Answer answerB;
    private Answer answerC;

    private LocationServiceImpl sut;

    @BeforeEach
    public void setup() {
        linkedInterventionIds = new ArrayList<>();
        linkedInterventionIds.add(interventionIdA);
        linkedInterventionIds.add(interventionIdB);
        linkedInterventionIds.add(interventionIdC);

        // arrange create DTO
        createLocationRequestDTO = Mockito.spy(
                new CreateLocationRequestDTO(name, delay, longitude, latitude, radius, areaId, franchiseId, linkedInterventionIds)
        );

        updateLocationRequestDTO = Mockito.spy(
                new UpdateLocationRequestDTO(id, name, delay, longitude, latitude, radius, areaId, franchiseId, linkedInterventionIds)
        );

        answerA = new Answer(answerAAnswer);
        answerB = new Answer(answerBAnswer);
        answerC = new Answer(answerCAnswer);

        answers = new ArrayList<>();

        answers.add(answerA);
        answers.add(answerB);
        answers.add(answerC);

        questions = new ArrayList<>();

        interventionA = new Command(interventionIdA, interventionNameA, commandA);
        interventionB = new Question(interventionIdB, interventionNameB, interventionQuestionB, answers);

        questions.add(interventionB);

        interventionC = new Questionnaire(interventionIdC, interventionNameC, questions);

        linkedInterventions = new ArrayList<>();

        linkedInterventions.add(interventionA);
        linkedInterventions.add(interventionB);
        linkedInterventions.add(interventionC);

        locationDAOFixture = Mockito.mock(LocationDAO.class);
        areaDAOFixture = Mockito.mock(AreaDAO.class);
        interventionDAOFixture = Mockito.mock(InterventionDAO.class);
        franchiseDAOFixture = Mockito.mock(FranchiseDAO.class);

        mockArea = new Area(areaId, areaName, areaLongitude, areaLatitude, areaRadius);
        mockFranchise = new Franchise(franchiseId, franchiseName);
        mockLocation = new Location(id, name, delay, longitude, latitude, radius, mockArea, mockFranchise, linkedInterventions);

        areaResponseDTO = AreaResponseDTO.fromData(mockArea);
        franchiseResponseDTO = FranchiseResponseDTO.fromData(mockFranchise);

        interventionResponseDTOs = linkedInterventions
                .stream()
                .map(InterventionResponseDTO::fromData)
                .collect(Collectors.toList());

        locationResponseDTO = new LocationResponseDTO(id, name, longitude, latitude, radius, areaResponseDTO, franchiseResponseDTO, delay, interventionResponseDTOs);


        // instantiate SUT
        sut = new LocationServiceImpl(locationDAOFixture, areaDAOFixture, franchiseDAOFixture);
    }

    @Test
    public void getLocationNotExisting() {
        // Arrange
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(null);

        // Act/Assert
        assertThrows(NotFoundException.class, () -> sut.getLocation(id));
    }

    @Test
    public void getLocation() {
        // Arrange
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act
        LocationResponseDTO actual = sut.getLocation(id);

        // Assert
        ObjectAssertions.assertEquals(locationResponseDTO, actual);
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
    public void testCreateChecksFranchiseExists() {
        // Arrange
        Mockito.when(franchiseDAOFixture.getFranchiseById(franchiseId)).thenReturn(null);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);

        // Act/Assert
        assertThrows(NotFoundException.class, () -> sut.createLocation(createLocationRequestDTO));
    }

    @Test
    public void testUpdateChecksFranchiseExists() {
        // Arrange
        Mockito.when(franchiseDAOFixture.getFranchiseById(franchiseId)).thenReturn(null);
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);
        Mockito.when(locationDAOFixture.getLocationById(id)).thenReturn(mockLocation);

        // Act/Assert
        assertThrows(NotFoundException.class, () -> sut.updateLocation(updateLocationRequestDTO));
    }

    @Test
    public void testCreateLocationVerifies() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);
        Mockito.when(franchiseDAOFixture.getFranchiseById(franchiseId)).thenReturn(mockFranchise);
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
        Mockito.when(franchiseDAOFixture.getFranchiseById(franchiseId)).thenReturn(mockFranchise);

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
                franchiseId,
                linkedInterventionIds
        );
    }

    @Test
    public void testUpdateChecksAreaExists() {
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(null);
        Mockito.when(franchiseDAOFixture.getFranchiseById(franchiseId)).thenReturn(null);

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
        Mockito.when(franchiseDAOFixture.getFranchiseById(areaId)).thenReturn(mockFranchise);
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
        Mockito.when(franchiseDAOFixture.getFranchiseById(areaId)).thenReturn(mockFranchise);
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
                franchiseId,
                linkedInterventionIds
        );
    }

    @Test
    public void testDeleteLocation() {
        // Arrange
        Mockito.when(locationDAOFixture.getLocationById(Mockito.anyInt())).thenReturn(new Location(id, name, delay, longitude, latitude, radius, mockArea, mockFranchise, linkedInterventions));
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
            add(new Location(id, name, delay, longitude, latitude, radius, mockArea, mockFranchise, linkedInterventions));
            add(new Location(id, name, delay, longitude, latitude, radius, mockArea, mockFranchise, linkedInterventions));
            add(new Location(id, name, delay, longitude, latitude, radius, mockArea, mockFranchise, linkedInterventions));
        }});

        //Act
        int actual = sut.getLocations().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
