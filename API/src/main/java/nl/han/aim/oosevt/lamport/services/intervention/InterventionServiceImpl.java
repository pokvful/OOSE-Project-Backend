package nl.han.aim.oosevt.lamport.services.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.intervention.InterventionDAO;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class InterventionServiceImpl implements InterventionService {
    private InterventionDAO interventionDAO;

    @Autowired
    public InterventionServiceImpl(InterventionDAO interventionDAO) {
        this.interventionDAO = interventionDAO;
    }

    private void assertInterventionExists(int id) {
        if (isNull(interventionDAO.getInterventionById(id))) {
            throw new NotFoundException();
        }
    }

    @Override
    public void updateCommand(UpdateCommandRequestDTO updateCommandRequestDTO) {
        updateCommandRequestDTO.validate();

        int id = updateCommandRequestDTO.getId();
        String name = updateCommandRequestDTO.getName();
        String command = updateCommandRequestDTO.getCommand();

        assertInterventionExists(id);

        interventionDAO.updateCommand(id, name, command);
    }
}
