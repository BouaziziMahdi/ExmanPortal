package com.bank.banking.Services;

import com.bank.banking.dto.Categorydto;
import com.bank.banking.dto.Quizdto;

import java.util.List;

public interface Quiz {
public Quizdto addQuiz(Quizdto quiz);
public Integer updateQuiz(Quizdto quizdto);
public List<Quizdto> getAllQuiz();
public Quizdto getQuizById(Integer id);
public void deleteQuiz(Integer id);
public List<Quizdto> getQuizOfCategory(Categorydto categorydto);
public List<Quizdto> getQuizOfCategoryAndActive(Categorydto categorydto);
public List<Quizdto> getActiveQuiz();
}
