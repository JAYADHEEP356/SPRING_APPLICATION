package com.example.quiz.Service;

import com.example.quiz.Entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    public void Save(Question question);
    public void DeleteById(int id);
    public void Update(Question employee);
    public Optional<Question> FindById(int id);
    public List<Question> findAll();



}
