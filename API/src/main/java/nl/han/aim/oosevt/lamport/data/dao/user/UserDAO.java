package nl.han.aim.oosevt.lamport.data.dao.user;

import nl.han.aim.oosevt.lamport.data.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    void updateUser(int id, String username, String email, String password, int roleId, int goalId);
    void createUser(String username, String email, String password, int roleId, int goalId);
    void deleteUser(int id);
}
