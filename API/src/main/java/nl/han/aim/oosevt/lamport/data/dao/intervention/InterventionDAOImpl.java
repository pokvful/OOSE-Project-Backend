package nl.han.aim.oosevt.lamport.data.dao.intervention;

import nl.han.aim.oosevt.lamport.data.entity.Intervention;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class InterventionDAOImpl implements InterventionDAO {

    private final static Logger LOGGER = Logger.getLogger(InterventionDAOImpl.class.getName());

    private Intervention interventionFromResultSet(ResultSet resultSet) {
        try {
            return new Intervention(
                    resultSet.getInt("intervention_id"),
                    resultSet.getString("intervention_name")
            );
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
        return null;
    }

    @Override
    public List<Intervention> getInterventionsByLocationId(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString()); PreparedStatement statement = connection.prepareStatement("CALL getInterventionsByLocationId(?)")) {
            statement.setInt(1, locationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Intervention> foundInterventions = new ArrayList<>();
                while (resultSet.next()) {
                    foundInterventions.add(interventionFromResultSet(resultSet));
                }

                return foundInterventions;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }

        return new ArrayList<>();
    }
}
