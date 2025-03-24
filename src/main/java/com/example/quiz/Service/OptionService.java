package com.example.quiz.Service;

import com.example.quiz.Entity.Option;
import com.example.quiz.Entity.Question;

import java.util.List;
import java.util.Optional;

public interface OptionService {

    public void Save(Option employee);
    public void DeleteById(int id);
    public void Update(Option employee);
    public Optional<Option> FindById(int id);
    public List<Option> findAll();
    public List<Option> findByQuestion(Question question);
    public Option FindByOptionText(String text);



}
