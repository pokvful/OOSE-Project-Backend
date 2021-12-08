package nl.han.aim.oosevt.lamport.data.dao.user;

import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UserDAOImpl implements UserDAO {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<User> getUsers() {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getUsers()");
             ResultSet resultSet = statement.executeQuery()) {

            List<User> getUsers = new ArrayList<>();
            while (resultSet.next()) {
                getUsers.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new Role(
                                resultSet.getInt("role_id"),
                                resultSet.getString("role_name"))));
            }
            return getUsers;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getUserById(?)")) {

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            new Role(
                                    resultSet.getInt("role_id"),
                                    resultSet.getString("role_name")));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "A database error occurred!", e);
        }
        return null;
    }
}
