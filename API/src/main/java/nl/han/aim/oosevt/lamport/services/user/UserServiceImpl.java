package nl.han.aim.oosevt.lamport.services.user;

import nl.han.aim.oosevt.lamport.controllers.user.dto.CreateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.shared.HashProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final HashProvider hashProvider;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, HashProvider hashProvider) {
        this.userDAO = userDAO;
        this.hashProvider = hashProvider;
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
        final String hash = hashProvider.hash(create.getPassword());
        userDAO.createUser(create.getUsername(), create.getEmail(), hash, create.getRoleId());
    }

    @Override
    public void deleteUser(int id) {
        if (userDAO.getUserById(id) == null) {
            throw new NotFoundException();
        }
        userDAO.deleteUser(id);
    }
}

