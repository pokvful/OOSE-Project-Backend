package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared;

import java.util.List;

public class QuestionRequestDTO extends InterventionRequestDTO {
    protected List<AnswerRequestDTO> answers;
    protected String question;

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

    @Override
    protected void validateDTO() {
        if(question == null || question.isEmpty()) {
            addError("questionText", "Vraagtekst mag niet leeg zijn!");
        }
    }
}
