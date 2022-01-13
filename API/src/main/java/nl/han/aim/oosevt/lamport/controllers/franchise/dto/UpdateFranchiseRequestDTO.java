package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

public class UpdateFranchiseRequestDTO extends FranchiseRequestDTO {
    private int id;

    public UpdateFranchiseRequestDTO(int id, String name) {
        super(name);
        this.id = id;
    }

    public UpdateFranchiseRequestDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
