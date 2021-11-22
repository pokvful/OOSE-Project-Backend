package nl.han.aim.oosevt.lamport.data.entity;

public class Intervention {
    private final int id;
    private final String name;

    public Intervention(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
