package nl.han.aim.oosevt.lamport.shared;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashProviderImpl implements HashProvider {

    private final BCryptPasswordEncoder bcrypt;

    public HashProviderImpl() {
        this.bcrypt = new BCryptPasswordEncoder();
    }

    public String hash(String input) {
        return bcrypt.encode(input);
    }

    public boolean matches(String input, String hash) {
        return bcrypt.matches(input, hash);
    }
}
