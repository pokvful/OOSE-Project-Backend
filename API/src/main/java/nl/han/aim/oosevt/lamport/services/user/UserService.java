package nl.han.aim.oosevt.lamport.services.user;

import nl.han.aim.oosevt.lamport.controllers.user.dto.CreateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UpdateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getUsers();
    UserResponseDTO getUserById(int id);
    void updateUser(UpdateUserRequestDTO updateUserRequestDTO);
    void createUser(CreateUserRequestDTO create);
    void deleteUser(int id);
}
