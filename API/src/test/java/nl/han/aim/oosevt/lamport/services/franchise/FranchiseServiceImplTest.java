package nl.han.aim.oosevt.lamport.services.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.CreateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.UpdateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FranchiseServiceImplTest {
    private final int invalidFranchiseId = 0;
    private final int deleteFranchiseId = 1;
    private final int id = 2;

    private final String name = "mcDonalds";

    private FranchiseDAO franchiseDAOFixture;

    private Franchise franchise;

    private FranchiseServiceImpl sut;

    private UpdateFranchiseRequestDTO updateFranchiseRequestDTO;

    private CreateFranchiseRequestDTO createFranchiseRequestDTO;

    @BeforeEach
    public void setup() {
        // Arrange create DTO
        createFranchiseRequestDTO = Mockito.spy(
                new CreateFranchiseRequestDTO(name));

        updateFranchiseRequestDTO = Mockito.spy(
                new UpdateFranchiseRequestDTO(id, name));

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

    @Test
    public void testCreateFranchiseVerifies() {
        // Arrange
        Mockito.when(franchiseDAOFixture.getFranchiseById(id)).thenReturn(franchise);

        // Act
        sut.createFranchise(createFranchiseRequestDTO);

        // Assert
        Mockito.verify(createFranchiseRequestDTO).validate();
    }

    @Test
    public void testCreateFranchiseCallsDB() {
        // Arrange
        Mockito.when(franchiseDAOFixture.getFranchiseById(id)).thenReturn(franchise);

        // Act
        sut.createFranchise(createFranchiseRequestDTO);

        // Assert
        Mockito.verify(franchiseDAOFixture).createFranchise(name);
    }

    @Test
    public void testUpdateChecksFranchiseExists() {
        Mockito.when(franchiseDAOFixture.getFranchiseById(id)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> sut.updateFranchise(updateFranchiseRequestDTO));
    }

    @Test
    public void testUpdateFranchiseVerifies() {
        // Arrange
        Mockito.when(franchiseDAOFixture.getFranchiseById(id)).thenReturn(franchise);

        // Act
        sut.updateFranchise(updateFranchiseRequestDTO);

        // Assert
        Mockito.verify(updateFranchiseRequestDTO).validate();
    }

    @Test
    public void testUpdateFranchiseUpdatesDB() {
        // Arrange
        Mockito.when(franchiseDAOFixture.getFranchiseById(id)).thenReturn(franchise);

        // Act
        sut.updateFranchise(updateFranchiseRequestDTO);

        // Assert
        Mockito.verify(franchiseDAOFixture).updateFranchise(id, name);
    }

    @Test
    public void testDeleteFranchise() {
        // Arrange
        Mockito.when(franchiseDAOFixture.getFranchiseById(Mockito.anyInt())).thenReturn(new Franchise(deleteFranchiseId, name));
        Mockito.doNothing().when(this.franchiseDAOFixture).deleteFranchise(Mockito.anyInt());

        // Act
        sut.deleteFranchise(deleteFranchiseId);

        // Assert
        Mockito.verify(franchiseDAOFixture).deleteFranchise(deleteFranchiseId);
    }

    @Test
    public void testDeleteFranchiseThrowsException() {
        assertThrows(NotFoundException.class, () -> sut.deleteFranchise(invalidFranchiseId));
    }
}
