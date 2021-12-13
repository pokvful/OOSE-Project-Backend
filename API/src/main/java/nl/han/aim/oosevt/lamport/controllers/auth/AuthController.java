package nl.han.aim.oosevt.lamport.controllers.auth;

import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginResponseDTO;
import nl.han.aim.oosevt.lamport.services.auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final LoginService loginService;

    @Autowired
    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping()
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginService.login(loginRequestDTO);
    }
}
