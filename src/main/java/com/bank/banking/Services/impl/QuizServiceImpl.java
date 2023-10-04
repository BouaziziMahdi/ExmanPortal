package com.bank.banking.Services.impl;

import com.bank.banking.Services.Quiz;

import com.bank.banking.dto.Categorydto;
import com.bank.banking.dto.Quizdto;
import com.bank.banking.repositories.QuizRepository;
import com.bank.banking.validator.ObjectsValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements Quiz {
    private final QuizRepository quizRepository;
    private final ObjectsValidator<Quizdto> validator;

    @Override
    public Quizdto addQuiz(Quizdto quiz) {
        validator.validate(quiz);
        quiz.getId();
        return Quizdto.FromEntity(quizRepository.save(Quizdto.toEntity(quiz)));
    }

    @Override
    public Integer updateQuiz(Quizdto quizdto) {
        var quiz = Quizdto.toEntity(quizdto);
        return quizRepository.save(quiz).getId();
    }

    @Override
    public List<Quizdto> getAllQuiz() {
        return quizRepository.findAll().stream()
                .map(Quizdto::FromEntity)
                .toList();
    }

    @Override
    public Quizdto getQuizById(Integer id) {
        return quizRepository.findById(id).stream()
                .map(Quizdto::FromEntity)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No quiz was found with the ID :" + id));
    }

    @Override
    public void deleteQuiz(Integer id) {
        quizRepository.deleteById(id);
    }

    @Override
    public List<Quizdto> getQuizOfCategory(Categorydto categorydto) {
        return this.quizRepository.findByCategory(Categorydto.toEntity(categorydto)).stream()
                .map(Quizdto::FromEntity)
                .toList();
    }

    @Override
    public List<Quizdto> getQuizOfCategoryAndActive(Categorydto categorydto) {
        return quizRepository.findByCategoryAndActive(Categorydto.toEntity(categorydto), true).stream()
                .map(Quizdto::FromEntity)
                .toList();
    }

    @Override
    public List<Quizdto> getActiveQuiz() {
        return quizRepository.findByActive(true).stream()
                .map(Quizdto::FromEntity)
                .toList();
    }
}