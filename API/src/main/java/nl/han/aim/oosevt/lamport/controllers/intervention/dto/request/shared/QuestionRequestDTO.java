package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

public abstract class QuestionRequestDTO extends InterventionRequestDTO {
    private AnswerRequestDTO answer;
    private String question;

    public QuestionRequestDTO(String name, AnswerRequestDTO answer, String question) {
        super(name);
        this.answer = answer;
        this.question = question;
    }

    public AnswerRequestDTO getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerRequestDTO answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
