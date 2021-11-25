package nl.han.aim.oosevt.lamport.services.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.CreateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.location.dto.UpdateLocationRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FranchiseServiceImpl implements FranchiseService {
    private final FranchiseDAO franchiseDAO;

    @Autowired
    public FranchiseServiceImpl(FranchiseDAO franchiseDAO) {
        this.franchiseDAO = franchiseDAO;
    }
    @Override
    public void createFranchise(CreateLocationRequestDTO location) {

    }

    @Override
    public void updateFranchise(UpdateLocationRequestDTO newData) {

    }

    @Override
    public void deleteFranchise(int id) {

    }

    @Override
    public FranchiseResponseDTO getFranchise(int id) {
        return new FranchiseResponseDTO().fromData(franchiseDAO.getFranchiseById(id));
    }

    @Override
    public List<FranchiseResponseDTO> getFranchises() {
        return franchiseDAO
                .getFranchises()
                .stream()
                .map(x -> new FranchiseResponseDTO().fromData(x))
                .collect(Collectors.toList());
    }
}
