package com.bank.banking.repositories;

import com.bank.banking.models.Category;
import com.bank.banking.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
    public List<Quiz> findByCategory(Category category);
    public List<Quiz> findByCategoryAndActive(Category category,boolean active);
    public List<Quiz> findByActive(boolean active);
}
