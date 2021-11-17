package nl.han.aim.oosevt.lamport.data.dao.area;

import nl.han.aim.oosevt.lamport.data.entity.Area;

import java.util.List;

public interface AreaDAO {

    void createArea(String name, double longitude, double latitude, int radius);
    Area getArea(int areaId);
    List<Area> getAreas();
    void updateArea(int areaId, String name, double longitude, double latitude, int radius);
    void deleteArea(int areaId);
}
