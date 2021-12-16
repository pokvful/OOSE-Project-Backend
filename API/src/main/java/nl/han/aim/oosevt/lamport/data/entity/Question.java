package nl.han.aim.oosevt.lamport.data.entity;

import java.util.List;

public class Question extends Intervention {
    private final String questionText;
    private final List<Answer> answers;

    public Question(int id, String name, String question, List<Answer> answers) {
        super(id, name);
        this.questionText = question;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getQuestionText() {
        return questionText;
    }
}
