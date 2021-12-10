package nl.han.aim.oosevt.lamport.controllers.user.dto;

public class UpdateUserRequestDTO extends UserRequestDTO {
    private int id;

    public UpdateUserRequestDTO(int id, String username, String email, String password, int roleId) {
        super(username, email, password, roleId);
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

    @Override
    protected void validateDTO() {
        super.validateDTO();
        if (id == 0) {
            addError("id", "Id mag niet leeg zijn!");
        }
    }

    protected void validateSpecificDTO() {
    }
}
