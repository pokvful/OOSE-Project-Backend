package nl.han.aim.oosevt.lamport.services.area;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.CreateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.area.dto.UpdateAreaRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.area.AreaDAO;
import nl.han.aim.oosevt.lamport.data.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AreaServiceImpl implements AreaService {

    private final AreaDAO dataAccess;

    @Autowired
    public AreaServiceImpl(AreaDAO dataAccess) {

        this.dataAccess = dataAccess;
    }

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

        return this.dataAccess.getAreas().stream()
                .map((areaEntity) -> new AreaResponseDTO().fromData(areaEntity))
                .collect(Collectors.toList());
    }
}
