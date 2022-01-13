package nl.han.aim.oosevt.lamport.controllers.intervention.dto.response;

import nl.han.aim.oosevt.lamport.data.entity.Command;
import nl.han.aim.oosevt.lamport.data.entity.Intervention;
import nl.han.aim.oosevt.lamport.data.entity.Question;
import nl.han.aim.oosevt.lamport.data.entity.Questionnaire;

public abstract class InterventionResponseDTO {
    private final int id;
    private final String name;

    public InterventionResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static InterventionResponseDTO fromData(Intervention intervention) {
        if (intervention instanceof Command) {
            return CommandResponseDTO.fromData((Command) intervention);
        }

        if (intervention instanceof Question) {
            return QuestionResponseDTO.fromData((Question) intervention);
        }

        if (intervention instanceof Questionnaire) {
            return QuestionnaireResponseDTO.fromData((Questionnaire) intervention);
        }

        return null;
    }
}
