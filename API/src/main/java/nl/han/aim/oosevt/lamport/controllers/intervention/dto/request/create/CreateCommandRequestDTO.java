package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.CommandRequestDTO;

public class CreateCommandRequestDTO extends CommandRequestDTO {
    public CreateCommandRequestDTO(String name, String command) {
        super(name, command);
    }

    public CreateCommandRequestDTO() {
    }
}
