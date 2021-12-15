package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import java.util.List;

public abstract class QuestionRequestDTO extends InterventionRequestDTO {
    private List<AnswerRequestDTO> answers;
    private String question;

    public QuestionRequestDTO() {}

    public QuestionRequestDTO(String name, List<AnswerRequestDTO> answers, String question) {
        super(name);
        this.answers = answers;
        this.question = question;
    }

    public List<AnswerRequestDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequestDTO> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
