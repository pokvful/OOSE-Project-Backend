package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.CommandRequestDTO;

public class CreateCommandRequestDTO extends CommandRequestDTO {
    private String text;

    @Override
    protected void validateDTO() {
    }

    public CreateCommandRequestDTO() {
    }

    public CreateCommandRequestDTO(String name, String command, String text) {
        super(name, command);
        this.text = text;
    }
}
