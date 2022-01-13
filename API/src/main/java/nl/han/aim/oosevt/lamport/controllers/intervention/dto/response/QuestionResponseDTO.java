package nl.han.aim.oosevt.lamport.controllers.intervention.dto.response;

import nl.han.aim.oosevt.lamport.data.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionResponseDTO extends InterventionResponseDTO {
    private final List<AnswerResponseDTO> answers;
    private final String question;
    private static final String type = "vraag";

    public QuestionResponseDTO(int id, String name, List<AnswerResponseDTO> answers, String question) {
        super(id, name);
        this.answers = answers;
        this.question = question;
    }

    public List<AnswerResponseDTO> getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getType() {
        return type;
    }

    public static QuestionResponseDTO fromData(Question question) {
        return new QuestionResponseDTO(
                question.getId(),
                question.getName(),
                question
                        .getAnswers()
                        .stream()
                        .map(AnswerResponseDTO::fromData)
                        .collect(Collectors.toList()),
                question.getQuestionText()
        );
    }
}
