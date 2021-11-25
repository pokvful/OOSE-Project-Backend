package nl.han.aim.oosevt.lamport.services.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.CreateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.UpdateFranchiseRequestDTO;

import java.util.List;

public interface FranchiseService {
    void createFranchise(CreateFranchiseRequestDTO franchise);
    void updateFranchise(UpdateFranchiseRequestDTO newData);
    void deleteFranchise(int id);
    FranchiseResponseDTO getFranchiseById(int id);
    List<FranchiseResponseDTO> getFranchises();
}
