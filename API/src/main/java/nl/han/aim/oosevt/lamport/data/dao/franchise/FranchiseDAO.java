package nl.han.aim.oosevt.lamport.data.dao.franchise;

import nl.han.aim.oosevt.lamport.data.entity.Franchise;

import java.util.List;

public interface FranchiseDAO {
    void createFranchise(String name);
    Franchise getFranchiseById(int franchiseId);
    List<Franchise> getFranchises();
    void updateFranchise(int franchiseId, String name);
    void deleteFranchise(int franchiseId);
}
