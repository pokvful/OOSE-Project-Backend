package nl.han.aim.oosevt.lamport.controllers.user.dto;

public class CreateUserRequestDTO extends UserRequestDTO {

    public CreateUserRequestDTO(String username, String email, String password, int roleId, int goalId) {
        super(username, email, password, roleId, goalId);
    }

    public CreateUserRequestDTO() {
    }

}
