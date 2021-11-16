package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAOImpl;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
}
