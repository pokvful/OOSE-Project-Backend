package nl.han.aim.oosevt.lamport.data.dao.role;

import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RoleDAOImpl implements RoleDAO {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<Role> getRoles() {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getRoles()");
             ResultSet resultSet = statement.executeQuery()) {

            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(
                        new Role(resultSet.getInt("role_id"),
                                resultSet.getString("role_name")));
            }
            return roles;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
        return new ArrayList<>();
    }
}
