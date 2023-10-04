package com.bank.banking.Services;

import com.bank.banking.dto.Questionsdto;
import com.bank.banking.dto.Quizdto;
import com.bank.banking.models.Questions;

import java.util.List;

public interface Question {
    public Questionsdto addQuestion(Questionsdto questionsdto);
    public Integer updateQuestion(Questionsdto questionsdto);
    public List<Questionsdto> getAllQuestions();
    public Questionsdto getQuestionById(Integer id);
  public List<Questionsdto> getQuestionOfQuiz(Quizdto quizdto);
   public void deleteQuestion(Integer id);
   public Questionsdto getQuestion(Integer id);

}
