package nl.han.aim.oosevt.lamport.data.dao.user;

import nl.han.aim.oosevt.lamport.data.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    User getUserById(int id);
}