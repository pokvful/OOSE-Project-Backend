package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;

import java.util.List;

public interface AreaService {
    void createArea(CreateAreaRequestDTO createAreaRequestDTO);
    void updateArea(UpdateAreaRequestDTO updateAreaRequestDTO);
    void deleteArea(int id);
    AreaResponseDTO getArea(int id);
    List<AreaResponseDTO> getAreas();
}
