package nl.han.aim.oosevt.lamport.data.dao.franchise;

import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class FranchiseDataMapperImpl implements FranchiseDataMapper {
    private static final Logger LOGGER = Logger.getLogger(FranchiseDataMapperImpl.class.getName());

    @Override
    public Franchise getFromResultSet(ResultSet resultSet) {
        try {
            return new Franchise(
                    resultSet.getInt("franchise_id"),
                    resultSet.getString("franchise_name"));
        } catch(SQLException e) {
            LOGGER.log(Level.SEVERE, "getFranchiseFromResultSet::A database error occurred!", e);
        }
        return null;
    }
}
