package nl.han.aim.oosevt.lamport.data.dao.location;

import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LocationDataMapperImpl implements LocationDataMapper {
    private static final Logger LOGGER = Logger.getLogger(LocationDataMapperImpl.class.getName());

    @Override
    public Location getFromResultSet(ResultSet resultSet) {
        try {
            int locationId = resultSet.getInt("location_id");
            return new Location(
                    locationId,
                    resultSet.getString("location_name"),
                    resultSet.getInt("delay"),
                    resultSet.getDouble("longitude"),
                    resultSet.getDouble("latitude"),
                    resultSet.getInt("radius"),
                    new Area(
                            resultSet.getInt("area_id"),
                            resultSet.getString("area_name"),
                            resultSet.getDouble("area_longitude"),
                            resultSet.getDouble("area_latitude"),
                            resultSet.getInt("area_radius")
                    ),
                    new Franchise(
                            resultSet.getInt("franchise_id"),
                            resultSet.getString("franchise_name")
                    ),
                    new ArrayList<>()
            );
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "LocationDataMapperImpl::An error occurred mapping the entity!", e);
        }

        return null;
    }
}
