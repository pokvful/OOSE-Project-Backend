package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AreaServiceImpl implements AreaService {

    private final AreaDAO areaDAO;

    @Autowired
    public AreaServiceImpl(AreaDAO areaDAO) {
        this.areaDAO = areaDAO;
    }

    @Override
    public void createArea(CreateAreaRequestDTO requestDTO) {
        areaDAO.createArea(requestDTO.getName(), requestDTO.getLongitude(), requestDTO.getLatitude(),
                requestDTO.getRadius());
    }

    @Override
    public void updateArea(UpdateAreaRequestDTO requestDTO) {
        if (areaDAO.getAreaById(requestDTO.getId()) == null) {
            throw new NotFoundException();
        }
        areaDAO.updateArea(requestDTO.getId(), requestDTO.getName(), requestDTO.getLongitude(),
                requestDTO.getLatitude(), requestDTO.getRadius());
    }

    @Override
    public void deleteArea(int id) {
        if (areaDAO.getAreaById(id) == null) {
            throw new NotFoundException();
        }
        areaDAO.deleteArea(id);
    }

    @Override
    public AreaResponseDTO getArea(int id) {

        final Area area = this.areaDAO.getAreaById(id);

        if (area == null) {
            throw new NotFoundException();
        }

        return new AreaResponseDTO().fromData(area);
    }

    @Override
    public List<AreaResponseDTO> getAreas() {

        return this.areaDAO.getAreas().stream().map((areaEntity) -> new AreaResponseDTO().fromData(areaEntity))
                .collect(Collectors.toList());
    }
}
