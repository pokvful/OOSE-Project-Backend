package nl.han.aim.oosevt.lamport.controllers.user;

import nl.han.aim.oosevt.lamport.controllers.user.dto.CreateUserRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.user.dto.UserResponseDTO;
import nl.han.aim.oosevt.lamport.services.user.UserService;
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

    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return new ResponseEntity<>(
                userService.getUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                userService.getUserById(id),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public void createUser(CreateUserRequestDTO create) {
        userService.createUser(create);
    }

    @DeleteMapping({"id"})
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }
}
