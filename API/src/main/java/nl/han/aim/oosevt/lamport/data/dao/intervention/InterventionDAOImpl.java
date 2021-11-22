package nl.han.aim.oosevt.lamport.data.dao.intervention;

import nl.han.aim.oosevt.lamport.data.entity.Area;
import nl.han.aim.oosevt.lamport.data.entity.Intervention;
import nl.han.aim.oosevt.lamport.data.entity.Location;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class InterventionDAOImpl implements InterventionDAO {
    private Intervention interventionFromResultSet(ResultSet resultSet) {
        try {
            return new Intervention(
                    resultSet.getInt("intervention_id"),
                    resultSet.getString("intervention_name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Intervention> getInterventionsByLocationId(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString()); PreparedStatement statement = connection.prepareStatement("CALL getInterventionsByLocationId(?)")) {
            statement.setInt(1, locationId);

            ResultSet resultSet = statement.executeQuery();

            List<Intervention> foundInterventions = new ArrayList<>();
            while (resultSet.next()) {
                foundInterventions.add(interventionFromResultSet(resultSet));
            }

            return foundInterventions;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
