package nl.han.aim.oosevt.lamport.data.entity;

public class Answer {
    private final int id;
    private final String answerText;

    public Answer(int answerId, String answer) {
        this.id = answerId;
        this.answerText = answer;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }
}
