package nl.han.aim.oosevt.lamport.services.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;

import java.util.List;

public interface FranchiseService {
    void createFranchise(CreateLocationRequestDTO location);
    void updateFranchise(UpdateLocationRequestDTO newData);
    void deleteFranchise(int id);
    FranchiseResponseDTO getFranchise(int id);
    List<FranchiseResponseDTO> getFranchises();
}
