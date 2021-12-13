package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

public abstract class CommandRequestDTO extends InterventionRequestDTO {
    private String command;

    public CommandRequestDTO(String name, String command) {
        super(name);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
