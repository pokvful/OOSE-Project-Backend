package nl.han.aim.oosevt.lamport.data.dao.intervention;

import nl.han.aim.oosevt.lamport.data.entity.Intervention;

import java.util.List;

public interface InterventionDAO {
    List<Intervention> getInterventionsByLocationId(int locationId);
    void updateCommand(int id, String name, String command);
    Intervention getInterventionById(int id);
}
