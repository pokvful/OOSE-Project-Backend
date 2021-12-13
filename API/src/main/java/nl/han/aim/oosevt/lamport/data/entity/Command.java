package nl.han.aim.oosevt.lamport.data.entity;

public class Command extends Intervention {
    private final String command;

    public Command(int id, String name, String command) {
        super(id, name);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
