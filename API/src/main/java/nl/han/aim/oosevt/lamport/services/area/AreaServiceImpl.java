package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaServiceImpl implements AreaService {
    @Override
    public void createArea(CreateAreaRequestDTO createAreaRequestDTO) {

    }

    @Override
    public void updateArea(UpdateAreaRequestDTO updateAreaRequestDTO) {

    }

    @Override
    public void deleteArea(int id) {

    }

    @Override
    public AreaResponseDTO getArea(int id) {
        return null;
    }

    @Override
    public List<AreaResponseDTO> getAreas() {
        return null;
    }
}
