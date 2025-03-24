package com.example.quiz.Service;


import com.example.quiz.Entity.Question;
import com.example.quiz.Repo.Question_Repo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionImpl implements QuestionService {

    private final Question_Repo repo;

    public QuestionImpl(Question_Repo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void Save(Question question) {
        repo.save(question);
    }

    @Override
    @Transactional
    public void DeleteById(int id) {
         repo.deleteById(id);
    }

    @Override
    @Transactional
    public void Update(Question employee) {
        repo.save(employee);
    }

    @Override
    public Optional<Question> FindById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Question> findAll() {
        return repo.findAll();
    }

    public int size(){
        return findAll().size();
    }
}
