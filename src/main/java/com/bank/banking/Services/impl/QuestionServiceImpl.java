package com.bank.banking.Services.impl;

import com.bank.banking.Services.Question;
import com.bank.banking.dto.Questionsdto;
import com.bank.banking.dto.Quizdto;
import com.bank.banking.models.Questions;
import com.bank.banking.repositories.QuestionRepository;
import com.bank.banking.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements Question {
    private final QuestionRepository questionRepository;
    private final ObjectsValidator<Questionsdto> validator ;
    public Questionsdto addQuestion(Questionsdto questionsdto) {
        Questions question = Questionsdto.toEntity(questionsdto);
        Questions savedQuestion = questionRepository.save(question);
        return Questionsdto.FromEntity(savedQuestion);
    }



    @Override
    public Integer updateQuestion(Questionsdto questionsdto) {
        validator.validate( questionsdto );
        Questions question =Questionsdto.toEntity( questionsdto );
        return questionRepository.save( question ).getId();
    }

    @Override
    public List<Questionsdto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map( Questionsdto::FromEntity )
                .toList();
    }

    @Override
    public Questionsdto getQuestionById(Integer id) {
        return questionRepository.findById(id).stream()
                .map( Questionsdto::FromEntity )
                .findAny()
                .orElseThrow(() -> new RuntimeException("No question was found with the ID :" + id));
    }

    @Override
    public List<Questionsdto> getQuestionOfQuiz(Quizdto quizdto) {
     return questionRepository.findByQuizId(quizdto.getId()).stream()
                .map(Questionsdto::FromEntity)
             .toList();

    }

    @Override
    public void deleteQuestion(Integer id) {
        Questionsdto questionsdto = getQuestionById(id);
        Questions question = Questionsdto.toEntity(questionsdto);
        questionRepository.delete(question);
    }


    @Override
    public Questionsdto getQuestion(Integer id) {
        Questionsdto questionsdto = getQuestionById(id);
        if (questionsdto != null) {
            return questionsdto;
        } else {
            throw new RuntimeException("No question was found with the ID :" + id);
    }
    }


}

