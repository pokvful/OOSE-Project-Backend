package nl.han.aim.oosevt.lamport.services.franchise;

import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.data.dao.location.LocationDAO;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import nl.han.aim.oosevt.lamport.data.entity.Intervention;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.services.location.LocationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FranchiseServiceImplTest {
    private final int id = 2;
    private final String name = "mcDonalds";

    private FranchiseDAO franchiseDAOFixture;

    private Franchise franchise;

    private FranchiseServiceImpl sut;

    @BeforeEach
    public void setup() {
        franchiseDAOFixture = Mockito.mock(FranchiseDAO.class);

        franchise = new Franchise(id, name);

        // instantiate SUT
        sut = new FranchiseServiceImpl(franchiseDAOFixture);
    }

    @Test
    public void testGetFranchises() {
        //Arrange
        Mockito.when(this.franchiseDAOFixture.getFranchises()).thenReturn(new ArrayList<>());

        //Act
        sut.getFranchises();

        //Assert
        Mockito.verify(this.franchiseDAOFixture).getFranchises();
    }

    @Test
    void getNoFranchises() {

        //Arrange
        var expected = 0;
        Mockito.when(this.franchiseDAOFixture.getFranchises()).thenReturn(new ArrayList<>());

        //Act
        int actual = sut.getFranchises().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getExistingFranchises() {

        //Arrange
        int expected = 3;

        Mockito.when(this.franchiseDAOFixture.getFranchises()).thenReturn(new ArrayList<>() {{
            add(new Franchise(id, name));
            add(new Franchise(id, name));
            add(new Franchise(id, name));
        }});

        //Act
        int actual = sut.getFranchises().size();

        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
