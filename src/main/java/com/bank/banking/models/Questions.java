package com.bank.banking.models;

import com.bank.banking.dto.Questionsdto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 5000)
    private String content;
    private String image;
    private String answer;
    private String option1;
    private String option2;
     private String option3;
     private String option4;
     @Transient
    private String givenAnswer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;


}
