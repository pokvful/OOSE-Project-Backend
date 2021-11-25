package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

public class UpdateFranchiseRequestDTO extends FranchiseRequestDTO {
    private int id;

    public UpdateFranchiseRequestDTO(int id, String name) {
        super(name);
        this.id = id;
    }

    public UpdateFranchiseRequestDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void validateSpecificDTO() {
        if(id == 0) {
            addError("id", "id kan niet leeg zijn!");
        }
    }
}
