package nl.han.aim.oosevt.lamport.controllers.auth.dto;

public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
