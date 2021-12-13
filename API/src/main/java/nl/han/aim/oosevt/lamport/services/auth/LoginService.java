package nl.han.aim.oosevt.lamport.services.auth;

import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
