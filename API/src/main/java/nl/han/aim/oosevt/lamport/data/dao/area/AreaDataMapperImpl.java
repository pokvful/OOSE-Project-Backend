package nl.han.aim.oosevt.lamport.data.dao.area;

import nl.han.aim.oosevt.lamport.data.entity.Area;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AreaDataMapperImpl implements AreaDataMapper {
    private static final Logger LOGGER = Logger.getLogger(AreaDataMapperImpl.class.getName());

    @Override
    public Area getFromResultSet(ResultSet resultSet) {
        try {
            return new Area(
                    resultSet.getInt("area_id"),
                    resultSet.getString("area_name"),
                    resultSet.getDouble("longitude"),
                    resultSet.getDouble("latitude"),
                    resultSet.getInt("radius"));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "AreaDataMapperImpl::An error occurred mapping the entity!", e);
        }
        return null;
    }
}
