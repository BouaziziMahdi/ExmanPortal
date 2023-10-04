package com.bank.banking.repositories;

import com.bank.banking.dto.Quizdto;
import com.bank.banking.models.Questions;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Integer> {

    List<Questions> findByQuizId(Integer id);
}
