package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

public abstract class CommandRequestDTO extends InterventionRequestDTO {
    private String commandText;

    public CommandRequestDTO() {
        super();
    }

    public CommandRequestDTO(String name, String command) {
        super(name);
        this.commandText = command;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    @Override
    protected void validateDTO() {
        if(name == null || name.isEmpty()) {
            addError("name", "Naam mag niet leeg zijn!");
        }
        if(commandText == null || commandText.isEmpty()) {
            addError("commandText", "Commando mag niet leeg zijn!");
        }
    }
}
