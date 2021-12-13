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

    private final static Logger LOGGER = Logger.getLogger(RoleDAOImpl.class.getName());

    @Override
    public List<Role> getRoles() {
        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL getRoles()");
                ResultSet resultSet = statement.executeQuery()
        ) {
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("role_id");
                String name = resultSet.getString("role_name");

                Role role = new Role(id, name);

                roles.add(role);
            }
            return roles;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Role getRoleById(int id) {
        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL getRoleById(?)")
        ) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                return new Role(resultSet.getInt("role_id"), resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A database error occurred!", e);
        }

        return null;
    }
}
