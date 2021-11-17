package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAOImpl;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class AreaServiceImplTest {

    private AreaServiceImpl sut;
    private AreaDAOImpl mockDAO;

    @BeforeEach
    public void setUp() {
        this.mockDAO = Mockito.mock(AreaDAOImpl.class);
        this.sut = new AreaServiceImpl(this.mockDAO);
    }

    @Test
    void getAreaCallsDAO() {

        //Arrange
        var areaId = 0;
        Mockito.when(this.mockDAO.getAreaById(Mockito.anyInt())).thenReturn(new Area(areaId, "", 0.0, 0.0, 0));

        //Act
        this.sut.getArea(areaId);

        //Assert
        Mockito.verify(this.mockDAO).getAreaById(Mockito.anyInt());
    }

    @Test
    void getExistingArea() {

        //Arrange
        var areaId = 0;
        Mockito.when(this.mockDAO.getAreaById(Mockito.anyInt())).thenReturn(new Area(areaId, "", 0.0, 0.0, 0));
        var expected = new AreaResponseDTO();

        //Act
        var actual = this.sut.getArea(areaId);

        //Assert
        Assertions.assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void getNoArea() {

        //Arrange
        Mockito.when(this.mockDAO.getAreaById(Mockito.anyInt())).thenReturn(null);

        //Assert
        Assertions.assertThrows(NotFoundException.class, (() -> this.sut.getArea(Mockito.anyInt())));
    }


    @Test
    void getAreasCallsDAO() {

        //Arrange
        Mockito.when(this.mockDAO.getAreas()).thenReturn(new ArrayList<>());

        //Act
        this.sut.getAreas();

        //Assert
        Mockito.verify(this.mockDAO).getAreas();
    }

    @Test
    void deleteAreaCallsDAO() {

        //Arrange
        var areaId = 0;
        Mockito.when(mockDAO.getAreaById(Mockito.anyInt())).thenReturn(new Area(areaId, "", 0.0, 0.0, 0));
        Mockito.doNothing().when(this.mockDAO).deleteArea(Mockito.anyInt());

        //Act
        this.sut.deleteArea(areaId);

        //Assert
        Mockito.verify(this.mockDAO).deleteArea(Mockito.anyInt());
    }

    @Test
    void deleteAreaThrowsExceptionOnNotExistingArea() {

        //Arrange
        Mockito.when(mockDAO.getAreaById(Mockito.anyInt())).thenReturn(null);

        //Act
        Assertions.assertThrows(NotFoundException.class, () -> this.sut.deleteArea(0));
    }
    
    @Test
    void createAreaCallsDAO() {

        //Arrange
        final String areaName = "Test";
        Mockito.doAnswer(x -> null).when(mockDAO).createArea(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());

        //Act
        this.sut.createArea(new CreateAreaRequestDTO(areaName, 10, 10, 10));

        //Assert
        Mockito.verify(this.mockDAO).createArea(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());
    }

    @Test
    void getExistingAreas() {

        //Arrange
        List<Area> mockResults = new ArrayList<>();
        var expected = 3;
        for (int i = 0; i < expected; i++) {

            mockResults.add(new Area(i, "", 0.0, 0.0, 0));
        }
        Mockito.when(this.mockDAO.getAreas()).thenReturn(mockResults);

        //Act
        var actual = this.sut.getAreas().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getNoAreas() {

        //Arrange
        var expected = 0;
        Mockito.when(this.mockDAO.getAreas()).thenReturn(List.of());

        //Act
        var actual = this.sut.getAreas().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateAreaCallsDAO() {

        //Arrange
        var areaId = 0;
        Mockito.when(mockDAO.getAreaById(Mockito.anyInt())).thenReturn(new Area(areaId, "", 0.0, 0.0, 0));
        Mockito.doNothing().when(this.mockDAO).updateArea(Mockito.anyInt(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());

        //Act
        this.sut.updateArea(new UpdateAreaRequestDTO(areaId, "test", 10D, 10D, 10));

        //Assert
        Mockito.verify(this.mockDAO).updateArea(Mockito.anyInt(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());
    }

    @Test
    void updateAreaThrowsExceptionOnNotExistingArea() {

        //Arrange
        Mockito.when(mockDAO.getAreaById(Mockito.anyInt())).thenReturn(null);

        //Act
        Assertions.assertThrows(NotFoundException.class, () -> this.sut.updateArea(new UpdateAreaRequestDTO(1, "test", 10D, 10D, 10)));
    }
}
