package nl.han.aim.oosevt.lamport.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.auth.dto.LoginResponseDTO;
import nl.han.aim.oosevt.lamport.data.dao.user.UserDAO;
import nl.han.aim.oosevt.lamport.data.entity.User;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import nl.han.aim.oosevt.lamport.exceptions.UnauthorizedException;
import nl.han.aim.oosevt.lamport.shared.HashProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LoginServiceImpl implements LoginService {

    private final UserDAO userDAO;
    private final HashProvider hashProvider;
    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class.getName());

    @Autowired
    public LoginServiceImpl(UserDAO userDAO, HashProvider hashProvider) {
        this.userDAO = userDAO;
        this.hashProvider = hashProvider;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        loginRequestDTO.validate();
        final User user = userDAO.getUserByUsername(loginRequestDTO.getUsername());

        if(user == null) {
            throw new InvalidDTOException();
        }

        if(!hashProvider.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new InvalidDTOException();
        }

        try {
            final String[] permissions = user
                    .getRole()
                    .getAllowedPermissions()
                    .toArray(new String[0]);
            return new LoginResponseDTO(getToken(loginRequestDTO, permissions));
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, "UnsupportedEncodingException", e);
        }
        return null;
    }

    private String getToken(LoginRequestDTO loginRequestDTO, String[] permissions) throws UnsupportedEncodingException {
        return JWT
                .create()
                .withIssuer("JITAI")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().toInstant().plus(2, ChronoUnit.DAYS).toEpochMilli()))
                .withClaim("name", loginRequestDTO.getUsername())
                .withArrayClaim("permissions", permissions)
                .sign(Algorithm.HMAC256(getJWTSecret()));
    }

    public String getJWTSecret() {
        return System.getenv("JWT_SECRET");
    }
}
