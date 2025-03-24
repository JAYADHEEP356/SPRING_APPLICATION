package com.example.quiz.Service;

import com.example.quiz.Entity.Option;
import com.example.quiz.Entity.Question;
import com.example.quiz.Repo.Option_Repo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionImpl implements OptionService {


    private final Option_Repo repo;

    public OptionImpl(Option_Repo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void Save(Option employee) {
        repo.save(employee);
    }

    @Override
    @Transactional
    public void DeleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void Update(Option employee) {
        repo.save(employee);
    }

    @Override
    public Optional<Option> FindById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Option> findAll() {
        return findAll();
    }

    @Override
    public List<Option> findByQuestion(Question question) {
        return repo.findOptionByQuestion(question);
    }

    @Override
    public Option FindByOptionText(String text) {
        return repo.findOptionByOptionText(text);
    }
}
