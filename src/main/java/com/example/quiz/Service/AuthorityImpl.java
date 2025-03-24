package com.example.quiz.Service;

import com.example.quiz.Entity.Authority;
import com.example.quiz.Repo.Authority_Repo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorityImpl implements AuthorityService{

    private final Authority_Repo repo;


    public AuthorityImpl(Authority_Repo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void Save(Authority authority) {
        repo.save(authority);
    }

    @Override
    @Transactional
    public void DeleteById(int id) {

        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void Update(Authority authority) {

        repo.save(authority);
    }

    @Override
    public Optional<Authority> FindById(int id) {

        return repo.findById(id);
    }

    @Override
    public List<Authority> findAll() {
        return repo.findAll();
    }
}
