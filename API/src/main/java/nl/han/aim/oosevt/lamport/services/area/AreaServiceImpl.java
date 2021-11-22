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
    public AreaServiceImpl(AreaDAO dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public void createArea(CreateAreaRequestDTO requestDTO) {
        dataAccess.createArea(requestDTO.getName(), requestDTO.getLongitude(), requestDTO.getLatitude(),
                requestDTO.getRadius());
    }

    @Override
    public void updateArea(UpdateAreaRequestDTO requestDTO) {
        if (dataAccess.getAreaById(requestDTO.getId()) == null) {
            throw new NotFoundException();
        }
        dataAccess.updateArea(requestDTO.getId(), requestDTO.getName(), requestDTO.getLongitude(),
                requestDTO.getLatitude(), requestDTO.getRadius());
    }

    @Override
    public void deleteArea(int id) {
        if (dataAccess.getAreaById(id) == null) {
            throw new NotFoundException();
        }
        dataAccess.deleteArea(id);
    }

    @Override
    public AreaResponseDTO getArea(int id) {

        final Area area = this.dataAccess.getAreaById(id);

        if (area == null) {
            throw new NotFoundException();
        }

        return new AreaResponseDTO().fromData(area);
    }

    @Override
    public List<AreaResponseDTO> getAreas() {

        return this.dataAccess.getAreas().stream().map((areaEntity) -> new AreaResponseDTO().fromData(areaEntity))
                .collect(Collectors.toList());
    }
}
