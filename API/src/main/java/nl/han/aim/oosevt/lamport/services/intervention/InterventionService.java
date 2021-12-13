package nl.han.aim.oosevt.lamport.services.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;

public interface InterventionService {
    void updateCommand(UpdateCommandRequestDTO updateCommandRequestDTO);
}
