package nl.han.aim.oosevt.lamport.services.user;

import nl.han.aim.oosevt.lamport.controllers.user.dto.CreateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UpdateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.role.RoleDAO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import nl.han.aim.oosevt.lamport.shared.HashProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final HashProvider hashProvider;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, HashProvider hashProvider) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.hashProvider = hashProvider;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userDAO
                .getUsers()
                .stream()
                .map(UserResponseDTO::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(int id) {
        final User user = userDAO.getUserById(id);

        if (user == null) {
            throw new NotFoundException();
        }

        return UserResponseDTO.fromData(user);
    }

    @Override
    public void updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        updateUserRequestDTO.validate();

        final int id = updateUserRequestDTO.getId();
        final String username = updateUserRequestDTO.getUsername();
        final String email = updateUserRequestDTO.getEmail();
        final String password = updateUserRequestDTO.getPassword();
        final int roleId = updateUserRequestDTO.getRoleId();
        final int goalId = updateUserRequestDTO.getGoalId();

        final String hash = new BCryptPasswordEncoder().encode(password);

        if (userDAO.getUserById(id) == null || roleDAO.getRoleById(roleId) == null) {
            throw new NotFoundException();
        }

        userDAO.updateUser(id, username, email, hash, roleId, goalId);
    }

    public void createUser(CreateUserRequestDTO create) {
        create.validate();
        final String hash = hashProvider.hash(create.getPassword());
        userDAO.createUser(create.getUsername(), create.getEmail(), hash, create.getRoleId(), create.getGoalId());
    }

    @Override
    public void deleteUser(int id) {
        if (userDAO.getUserById(id) == null) {
            throw new NotFoundException();
        }
        userDAO.deleteUser(id);
    }
}
