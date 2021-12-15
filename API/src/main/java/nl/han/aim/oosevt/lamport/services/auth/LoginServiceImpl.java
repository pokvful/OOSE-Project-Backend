package nl.han.aim.oosevt.lamport.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.UnauthorizedException;
import nl.han.aim.oosevt.lamport.shared.HashProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class LoginServiceImpl implements LoginService {

    private final UserDAO userDAO;
    private final HashProvider hashProvider;

    @Autowired
    public LoginServiceImpl(UserDAO userDAO, HashProvider hashProvider) {
        this.userDAO = userDAO;
        this.hashProvider = hashProvider;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        final User user = userDAO.getUserByUsername(loginRequestDTO.getUsername());

        if(user == null) {
            throw new UnauthorizedException();
        }

        if(!hashProvider.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new UnauthorizedException();
        }

        try {
            final String[] permissions = new String[1];
            permissions[0] = "Test";
            return new LoginResponseDTO(JWT
                    .create()
                    .withIssuer("JITAI")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(new Date().toInstant().plus(2, ChronoUnit.DAYS).toEpochMilli()))
                    .withClaim("name", loginRequestDTO.getUsername())
                    .withArrayClaim("permissions", permissions)
                    .sign(Algorithm.HMAC256(System.getenv("JWT_SECRET"))));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}