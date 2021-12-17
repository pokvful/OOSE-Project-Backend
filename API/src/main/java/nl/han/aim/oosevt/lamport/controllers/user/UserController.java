package nl.han.aim.oosevt.lamport.controllers.user;

import nl.han.aim.oosevt.lamport.controllers.user.dto.UpdateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.CreateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;
import nl.han.aim.oosevt.lamport.services.user.UserService;
import nl.han.aim.oosevt.lamport.shared.Permission;
import nl.han.aim.oosevt.lamport.shared.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping()
    @Permission(permission = Permissions.UPDATE_USERS)
    public void updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        userService.updateUser(updateUserRequestDTO);
    }

    @GetMapping("")
    @Permission(permission = Permissions.GET_USERS)
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return new ResponseEntity<>(
                userService.getUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    @Permission(permission = Permissions.GET_USERS)
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                userService.getUserById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("")
    @Permission(permission = Permissions.CREATE_USERS)
    public void createUser(@RequestBody CreateUserRequestDTO create) {
        userService.createUser(create);
    }

    @DeleteMapping("{id}")
    @Permission(permission = Permissions.DELETE_USERS)
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }
}
