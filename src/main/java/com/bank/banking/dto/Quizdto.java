package com.bank.banking.dto;

import com.bank.banking.models.Category;
import com.bank.banking.models.Questions;
import com.bank.banking.models.Quiz;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quizdto {
    private Integer id;
    @NotNull(message = "Le title ne doit pas etre vide")
    private String title;
    private String description;
    @NotNull(message = "Le MaxMarks ne doit pas etre vide")
    private String MaxMarks;
    private boolean active = false;
    private String numberOfQuestion;
  private List<Questionsdto> questions=new ArrayList<>();;
    private Categorydto category;
    public static Quiz toEntity(Quizdto quizdto) {
        return Quiz.builder()
                .id(quizdto.getId())
                .title(quizdto.getTitle())
                .description(quizdto.getDescription())
                .MaxMarks(quizdto.getMaxMarks())
                .active(quizdto.isActive())
                .numberOfQuestion(quizdto.getNumberOfQuestion())
                .category(Category.builder()
                    .id(quizdto.getCategory().getId())
                     .build())

                .build();
    }

    public static Quizdto FromEntity(Quiz quiz) {
        return Quizdto.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .MaxMarks(quiz.getMaxMarks())
                .active(quiz.isActive())
                .numberOfQuestion(quiz.getNumberOfQuestion())
                .category(Categorydto.builder()
                        .id(quiz.getCategory().getId())
                        .build())
                .build();
    }


}
