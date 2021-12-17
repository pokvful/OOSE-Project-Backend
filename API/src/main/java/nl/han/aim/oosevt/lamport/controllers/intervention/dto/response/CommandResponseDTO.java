package nl.han.aim.oosevt.lamport.controllers.intervention.dto.response;

import nl.han.aim.oosevt.lamport.data.entity.Command;

public class CommandResponseDTO extends InterventionResponseDTO {
    private String command;

    public CommandResponseDTO(int id, String name, String command) {
        super(id, name);
        this.command = command;
    }

    public static CommandResponseDTO fromData(Command command) {
        return new CommandResponseDTO(command.getId(), command.getName(), command.getCommandText());
    }
}
