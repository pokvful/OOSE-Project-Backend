package nl.han.aim.oosevt.lamport.services.user;

import nl.han.aim.oosevt.lamport.controllers.user.dto.CreateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userDAO
                .getUsers()
                .stream()
                .map(x -> new UserResponseDTO().fromData(x))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(int id) {
        final User user = userDAO.getUserById(id);

        if (user == null) {
            throw new NotFoundException();
        }

        return new UserResponseDTO().fromData(user);
    }

    @Override
    public void createUser(CreateUserRequestDTO create) {
        create.validate();
        userDAO.createUser(create.getUsername(), create.getEmail(), create.getPassword(), create.getRole().getRoleId());
    }
}

