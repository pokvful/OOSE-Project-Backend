package nl.han.aim.oosevt.lamport.controllers.user.dto;

public class CreateUserRequestDTO extends UserRequestDTO {

    public CreateUserRequestDTO(String username, String email, String password, int roleId) {
        super(username, email, password, roleId);
    }

    public CreateUserRequestDTO() {
    }

}
