package nl.han.aim.oosevt.lamport.data.dao.area;

import nl.han.aim.oosevt.lamport.data.entity.Area;

import java.util.List;

public interface AreaDAO {
    List<Area> getAreas();
    void createArea(String name, double longitude, double latitude, int radius);
    Area getArea(int areaId);
    void deleteArea(int id);
    void updateArea(int id, String name, double longitude, double latitude, int radius);
}
