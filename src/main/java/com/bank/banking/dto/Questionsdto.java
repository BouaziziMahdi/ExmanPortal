package com.bank.banking.dto;
import com.bank.banking.models.Questions;
import com.bank.banking.models.Quiz;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Questionsdto {
    private Integer id;
    @NotNull(message = "Le content ne doit pas etre vide")
    private String content;
    private String image;
    @NotNull(message = "Le answer ne doit pas etre vide")
    private String answer;
    @NotNull(message = "Le option1 ne doit pas etre vide")
    private String option1;
    @NotNull(message = "Le option2 ne doit pas etre vide")
    private String option2;
    @NotNull(message = "Le option3 ne doit pas etre vide")
    private String option3;
    @NotNull(message = "Le option4 ne doit pas etre vide")
    private String option4;
    @NotNull(message = "Le quiz ne doit pas etre vide")

    private Quizdto quiz;
    private String givenAnswer;
    public static Questions toEntity(Questionsdto questionsdto) {
        return Questions.builder()
                .id( questionsdto.getId())
                .content(questionsdto.getContent())
                .image(questionsdto.getImage())
                .answer(questionsdto.getAnswer())
                .option1(questionsdto.getOption1())
                .option2(questionsdto.getOption2())
                .option3(questionsdto.getOption3())
                .option4(questionsdto.getOption4())
                .givenAnswer(questionsdto.getGivenAnswer())
                .quiz(Quiz.builder()
                        .id(questionsdto.getQuiz().getId())
                        .build())
                .build();
    }

    public static Questionsdto FromEntity(Questions questions) {
        return Questionsdto.builder()
                .id(questions.getId())
                .content(questions.getContent())
                .image(questions.getImage())
                .answer(questions.getAnswer())
                .option1(questions.getOption1())
                .option2(questions.getOption2())
                .option3(questions.getOption3())
                .option4(questions.getOption4())
                .givenAnswer(questions.getGivenAnswer())
                .quiz(Quizdto.builder()
                        .id(questions.getQuiz().getId())
                        .build())
                .build();
    }
}
