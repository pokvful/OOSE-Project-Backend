package nl.han.aim.oosevt.lamport.data.dao.user;

import nl.han.aim.oosevt.lamport.data.dao.role.RoleDAO;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

    private final RoleDAO roleDAO;

    @Autowired
    public UserDAOImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<User> getUsers() {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL getUsers()");
                ResultSet resultSet = statement.executeQuery()) {

            List<User> getUsers = new ArrayList<>();
            while (resultSet.next()) {
                getUsers.add(getUserFromResultSet(resultSet));
            }
            return getUsers;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getUsers::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL getUserById(?)")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getUserById::A database error occurred!", e);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getUserByUsername(?)")) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getUserByUsername::A database error occurred!", e);
        }
        return null;
    }

    @Override
    public void updateUser(int id, String username, String email, String password, int roleId) {
        try (
                Connection connection = DriverManager.getConnection(connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL updateUser(?, ?, ?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setInt(5, roleId);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateUser::A database error occurred!", e);
        }
    }

    @Override
    public void createUser(String username, String email, String password, int roleId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL createUser(?, ?, ?, ?)")) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setInt(4, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "createUser::A database error occurred!", e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteUser(?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "deleteUser::A database error occurred!", e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            return new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    roleDAO.getRoleById(resultSet.getInt("role_id"))
            );
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getUserFromResultSet::A database error occurred!", e);
        }

        return null;
    }
}
