package com.bank.banking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private    String description;
    private String MaxMarks;
    private boolean active = false;
   private String numberOfQuestion;
    @OneToMany(mappedBy = "quiz", fetch =FetchType.LAZY, cascade = CascadeType.ALL )
    @JsonIgnore
    private List<Questions> questions;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
