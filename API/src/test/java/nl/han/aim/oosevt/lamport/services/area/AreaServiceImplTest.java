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
/*
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
        Mockito.when(this.mockDAO.getArea(Mockito.anyInt())).thenReturn(new Area());

        //Act
        this.sut.getArea(0);

        //Assert
        Mockito.verify(this.mockDAO).getArea(Mockito.anyInt());
    }

    @Test
    void getExistingArea() {

        //Arrange
        Mockito.when(this.mockDAO.getArea(Mockito.anyInt())).thenReturn(new Area());
        var expected = new AreaResponseDTO();

        //Act
        var actual = this.sut.getArea(0);

        //Assert
        Assertions.assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    void getNoArea() {

        //Arrange
        Mockito.when(this.mockDAO.getArea(Mockito.anyInt())).thenReturn(null);

        //Assert
        Assertions.assertThrows(NotFoundException.class, (() -> this.sut.getArea(0)));
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
        Mockito.when(mockDAO.getArea(Mockito.anyInt())).thenReturn(new Area());
        Mockito.doNothing().when(this.mockDAO).deleteArea(Mockito.anyInt());

        //Act
        this.sut.deleteArea(1);

        //Assert
        Mockito.verify(this.mockDAO).deleteArea(1);
    }

    @Test
    void deleteAreaThrowsExceptionOnNotExistingArea() {

        //Arrange
        Mockito.when(mockDAO.getArea(Mockito.anyInt())).thenReturn(null);

        //Act
        Assertions.assertThrows(NotFoundException.class, () -> this.sut.deleteArea(1));
    }
    
    @Test
    void createAreaCallsDAO() {
        final String areaName = "Test";
        //Arrange
        Mockito.doAnswer(x -> null).when(mockDAO).createArea(areaName, 10, 10, 10);

        //Act
        this.sut.createArea(new CreateAreaRequestDTO(areaName, 10, 10, 10));

        //Assert
        Mockito.verify(this.mockDAO).createArea(areaName, 10, 10, 10);
    }

    @Test
    void getExistingAreas() {

        //Arrange
        var expected = 3;
        Mockito.when(this.mockDAO.getAreas()).thenReturn(List.of(
                new Area(),
                new Area(),
                new Area()
        ));

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
        Mockito.when(mockDAO.getArea(Mockito.anyInt())).thenReturn(new Area());
        Mockito.doNothing().when(this.mockDAO).updateArea(Mockito.anyInt(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());

        //Act
        this.sut.updateArea(new UpdateAreaRequestDTO(1, "test", 10D, 10D, 10));

        //Assert
        Mockito.verify(this.mockDAO).updateArea(Mockito.anyInt(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());
    }

    @Test
    void updateAreaThrowsExceptionOnNotExistingArea() {

        //Arrange
        Mockito.when(mockDAO.getArea(Mockito.anyInt())).thenReturn(null);

        //Act
        Assertions.assertThrows(NotFoundException.class, () -> this.sut.updateArea(new UpdateAreaRequestDTO(1, "test", 10D, 10D, 10)));
    }
}
*/