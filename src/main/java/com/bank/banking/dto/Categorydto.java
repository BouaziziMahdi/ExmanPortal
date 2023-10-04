package com.bank.banking.dto;

import com.bank.banking.models.Category;
import com.bank.banking.models.Quiz;
import com.bank.banking.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categorydto {
   private Integer id;
    @NotNull(message = "title is mandatory")
   private String title;
    @NotNull(message = "description is mandatory")
    private String description;
    private List<Quizdto> quizzes;
    public static Category toEntity(Categorydto categorydto) {
        return Category.builder()
                .id( categorydto.getId())
                .title(categorydto.getTitle())
                .description(categorydto.getDescription())
                .build();
    }

    public static Categorydto FromEntity(Category category) {
        return Categorydto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

}
