package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

public abstract class CommandRequestDTO extends InterventionRequestDTO {
    @NotEmpty
    @TranslatedName(name = "Tekst")
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
}
