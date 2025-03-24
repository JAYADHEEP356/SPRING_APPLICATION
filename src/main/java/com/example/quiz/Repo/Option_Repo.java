package com.example.quiz.Repo;

import com.example.quiz.Entity.Option;
import com.example.quiz.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Option_Repo extends JpaRepository<Option,Integer> {

    List<Option> findOptionByQuestion(Question question);

    Option findOptionByOptionText(String optionText);
}
