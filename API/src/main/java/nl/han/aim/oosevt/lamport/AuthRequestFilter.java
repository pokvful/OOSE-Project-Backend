package nl.han.aim.oosevt.lamport;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import nl.han.aim.oosevt.lamport.exceptions.ForbiddenException;
import nl.han.aim.oosevt.lamport.exceptions.UnauthorizedException;
import nl.han.aim.oosevt.lamport.shared.Permission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthRequestFilter implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(AuthRequestFilter.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            //If the method doesn't have a permission annotation, it
            //doesn't require one to access. So, you always can.
            if (!handlerMethod.hasMethodAnnotation(Permission.class)) {
                return true;
            }

            Permission annotation = handlerMethod.getMethodAnnotation(Permission.class);

            final String requiredPermission = annotation.permission().name();
            final String jwtToken = request.getHeader("Authorization");

            //If the token is not in the header the user doesn't have a token
            if(jwtToken == null) {
                throw new UnauthorizedException();
            }

            final DecodedJWT jwt = decodeJWT(jwtToken.replace("Bearer ", ""));

            final List<String> permissions = getPermissionsFromString(jwt);

            //If the permission is not in the list of permissions in the token
            //the user can't access it.
            if(!permissions.contains(requiredPermission)) {
                throw new ForbiddenException();
            }
        }
        return true;
    }

    private List<String> getPermissionsFromString(DecodedJWT token) {
        return Arrays.asList(token
                .getClaim("permissions")
                .asArray(String.class));
    }

    private DecodedJWT decodeJWT(String token) {
        try {
            return JWT
                    .require(Algorithm.HMAC256(System.getenv("JWT_SECRET")))
                    .build()
                    .verify(token);
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, "UnsupportedEncodingException", e);
        } catch(SignatureVerificationException e) {
            LOGGER.log(Level.SEVERE, "SignatureVerificationException", e);
            throw new UnauthorizedException();
        }

        //Token couldn't be verified, so it is invalid.
        throw new UnauthorizedException();
    }
}
