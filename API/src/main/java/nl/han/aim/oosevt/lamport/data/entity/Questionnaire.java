package nl.han.aim.oosevt.lamport.data.entity;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;

import java.util.List;

public class Questionnaire extends Intervention {
    private final List<Question> questions;

    public Questionnaire(int id, String name, List<Question> questions) {
        super(id, name);
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
