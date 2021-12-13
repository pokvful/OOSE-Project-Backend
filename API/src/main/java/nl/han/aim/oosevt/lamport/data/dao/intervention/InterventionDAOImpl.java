package nl.han.aim.oosevt.lamport.data.dao.intervention;

import nl.han.aim.oosevt.lamport.data.entity.Command;
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
            return new Command(
                    resultSet.getInt("intervention_id"),
                    resultSet.getString("intervention_name"),
                    resultSet.getString("command")
            );
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
        return null;
    }

    @Override
    public List<Intervention> getInterventionsByLocationId(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString()); PreparedStatement statement = connection.prepareStatement("CALL getCommandsByLocationId(?)")) {
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

    @Override
    public void updateCommand(int id, String name, String command) {

        try (
            Connection connection = DriverManager.getConnection(connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL updateCommand(?, ?, ?)")
        ) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, command);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
    }

    @Override
    public Intervention getInterventionById(int id) {
        return new Command(1, "mock intervention from InterventionDAOImpl", "ga naar saladebami");
    }
}
