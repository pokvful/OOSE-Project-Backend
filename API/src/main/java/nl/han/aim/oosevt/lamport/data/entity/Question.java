package nl.han.aim.oosevt.lamport.data.entity;

import java.util.List;

public class Question extends Intervention {
    private String question;
    private List<Answer> answers;

    public Question(int id, String name, String question, List<Answer> answers) {
        super(id, name);
        this.question = question;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
