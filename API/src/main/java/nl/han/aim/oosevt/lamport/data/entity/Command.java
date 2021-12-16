package nl.han.aim.oosevt.lamport.data.entity;

public class Command extends Intervention {
    private final String commandText;

    public Command(int id, String name, String command) {
        super(id, name);
        this.commandText = command;
    }

    public String getCommandText() {
        return commandText;
    }
}
