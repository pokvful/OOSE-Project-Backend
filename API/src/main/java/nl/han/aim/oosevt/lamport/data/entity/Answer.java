package nl.han.aim.oosevt.lamport.data.entity;

public class Answer {
    private final int answerId;
    private final String answerText;

    public Answer(int answerId, String answer) {
        this.answerId = answerId;
        this.answerText = answer;
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getAnswerText() {
        return answerText;
    }
}
