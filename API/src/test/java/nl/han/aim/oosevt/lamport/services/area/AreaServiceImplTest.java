package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.ObjectAssertions;
import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAOImpl;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class AreaServiceImplTest {

    private final int areaId = 1;
    private final String name = "Nijmegen";
    private final double longitude = 42.312;
    private final double latitude = 32.321;
    private final int radius = 1000;

    private Area mockArea;
    private AreaResponseDTO mockAreaResponseDTO;
    private ArrayList<AreaResponseDTO> areasResponseDTO;
    private ArrayList<Area> mockAreas;
    private CreateAreaRequestDTO createAreaRequestDTO;
    private UpdateAreaRequestDTO updateAreaRequestDTO;

    private AreaServiceImpl sut;
    private AreaDAOImpl areaDAOFixture;

    @BeforeEach
    public void setup() {
        mockArea = new Area(areaId, name, longitude, latitude, radius);

        areaDAOFixture = Mockito.mock(AreaDAOImpl.class);

        mockAreaResponseDTO = new AreaResponseDTO(areaId, name, longitude, latitude, radius);
        createAreaRequestDTO = new CreateAreaRequestDTO(name, longitude, latitude, radius);
        updateAreaRequestDTO = new UpdateAreaRequestDTO(areaId, name, longitude, latitude, radius);

        mockAreas = new ArrayList<>();
        mockAreas.add(mockArea);

        areasResponseDTO = new ArrayList<>();
        areasResponseDTO.add(mockAreaResponseDTO);

        sut = new AreaServiceImpl(areaDAOFixture);
    }

    @Test
    void getAreaCallsDAO() {
        //Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);

        //Act
        sut.getArea(areaId);

        //Assert
        Mockito.verify(areaDAOFixture).getAreaById(areaId);
    }

    @Test
    void getExistingArea() {
        //Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);

        //Act
        AreaResponseDTO actual = sut.getArea(areaId);

        //Assert
        ObjectAssertions.assertEquals(mockAreaResponseDTO, actual);
    }

    @Test
    void getNoArea() {
        //Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(null);
        Executable executable = () -> sut.getArea(areaId);

        //Assert
        Assertions.assertThrows(NotFoundException.class, executable);
    }


    @Test
    void getAreasCallsDAO() {
        //Act
        sut.getAreas();

        //Assert
        Mockito.verify(areaDAOFixture).getAreas();
    }

    @Test
    void deleteAreaCallsDAO() {
        // Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);

        //Act
        sut.deleteArea(areaId);

        //Assert
        Mockito.verify(areaDAOFixture).deleteArea(areaId);
    }

    @Test
    void deleteAreaThrowsExceptionOnNotExistingArea() {
        //Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(null);

        //Act
        Assertions.assertThrows(NotFoundException.class, () -> sut.deleteArea(areaId));
    }

    @Test
    void createAreaCallsDAO() {
        //Act
        sut.createArea(createAreaRequestDTO);

        //Assert
        Mockito.verify(areaDAOFixture).createArea(name, longitude, latitude, radius);
    }

    @Test
    void getExistingAreas() {
        //Arrange
        Mockito.when(areaDAOFixture.getAreas()).thenReturn(mockAreas);

        //Act
        List<AreaResponseDTO> actual = sut.getAreas();

        //Assert
        ObjectAssertions.assertEquals(areasResponseDTO, actual);
    }

    @Test
    void getNoAreas() {
        //Arrange
        areasResponseDTO.clear();
        mockAreas.clear();

        Mockito.when(areaDAOFixture.getAreas()).thenReturn(mockAreas);

        //Act
        List<AreaResponseDTO> actual = sut.getAreas();

        //Assert
        ObjectAssertions.assertEquals(areasResponseDTO, actual);
    }

    @Test
    void updateAreaCallsDAO() {
        //Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(mockArea);

        //Act
        sut.updateArea(updateAreaRequestDTO);

        //Assert
        Mockito.verify(areaDAOFixture).updateArea(areaId, name, longitude, latitude, radius);
    }

    @Test
    void updateAreaThrowsExceptionOnNotExistingArea() {
        //Arrange
        Mockito.when(areaDAOFixture.getAreaById(areaId)).thenReturn(null);
        Executable executable = () -> sut.updateArea(updateAreaRequestDTO);

        //Act
        Assertions.assertThrows(NotFoundException.class, executable);
    }
}
