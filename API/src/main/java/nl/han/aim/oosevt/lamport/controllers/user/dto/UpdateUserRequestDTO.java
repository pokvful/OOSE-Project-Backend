package nl.han.aim.oosevt.lamport.controllers.user.dto;

public class UpdateUserRequestDTO extends UserRequestDTO {
    private int id;

    public UpdateUserRequestDTO(int id, String username, String email, String password, int roleId, int goalId) {
        super(username, email, password, roleId, goalId);
        this.id = id;
    }

    public UpdateUserRequestDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
