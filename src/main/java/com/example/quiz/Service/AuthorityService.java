package com.example.quiz.Service;

import com.example.quiz.Entity.Authority;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {

    public void Save(Authority authority);
    public void DeleteById(int id);
    public void Update(Authority authority);
    public Optional<Authority> FindById(int id);
    public List<Authority> findAll();

}
