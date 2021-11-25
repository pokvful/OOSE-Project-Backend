package nl.han.aim.oosevt.lamport.data.entity;

public class Franchise {
    private final int franchiseId;
    private final String franchiseName;

    public Franchise(int franchiseId, String franchiseName) {
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public String getFranchiseName() {
        return franchiseName;
    }
}
