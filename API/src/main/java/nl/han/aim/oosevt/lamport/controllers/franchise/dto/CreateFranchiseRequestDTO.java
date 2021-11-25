package nl.han.aim.oosevt.lamport.controllers.franchise.dto;

public class CreateFranchiseRequestDTO extends FranchiseRequestDTO {
    private int id;

    public CreateFranchiseRequestDTO(String name) {
        super(name);
    }

    public CreateFranchiseRequestDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
