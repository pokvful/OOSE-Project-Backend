package nl.han.aim.oosevt.lamport.controllers.user.dto;

import nl.han.aim.oosevt.lamport.data.entity.Role;

public class CreateUserRequestDTO extends UserRequestDTO {

    public CreateUserRequestDTO(String username, String email, String password, Role role) {
        super(username, email, password, role);
    }

    public CreateUserRequestDTO() {

    }
}
