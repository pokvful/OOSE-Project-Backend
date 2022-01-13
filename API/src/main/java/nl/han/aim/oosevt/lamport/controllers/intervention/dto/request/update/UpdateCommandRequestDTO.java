package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.CommandRequestDTO;

public class UpdateCommandRequestDTO extends CommandRequestDTO {
    private int id;

    public UpdateCommandRequestDTO() {}

    public UpdateCommandRequestDTO(String name, String command, int id) {
        super(name, command);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
