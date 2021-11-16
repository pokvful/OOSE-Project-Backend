package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAOImpl;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
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
    void getAreasCallsDAO() {

        //Arrange
        Mockito.when(this.mockDAO.getAreas()).thenReturn(new ArrayList<>());

        //Act
        this.sut.getAreas();

        //Assert
        Mockito.verify(this.mockDAO).getAreas();
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
}
