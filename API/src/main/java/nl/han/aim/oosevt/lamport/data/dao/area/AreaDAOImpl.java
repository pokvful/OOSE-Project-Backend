package nl.han.aim.oosevt.lamport.data.dao.area;

import nl.han.aim.oosevt.lamport.data.entity.Area;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaDAOImpl implements AreaDAO {
    @Override
    public List<Area> getAreas() {
        return null;
    }

    @Override
    public void createArea(String name, double longitude, double latitude, int radius) {

    }
}
