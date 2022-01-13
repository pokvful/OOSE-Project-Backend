package nl.han.aim.oosevt.lamport.controllers.auth.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

public class LoginRequestDTO extends RequestDTO {
    @NotEmpty
    @TranslatedName(name = "Gebruikersnaam")
    private String username;

    @NotEmpty
    @TranslatedName(name = "Wachtwoord")
    private String password;

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequestDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }
}
