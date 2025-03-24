package com.example.quiz.Service;

import com.example.quiz.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void Save(User user);
    public void DeleteById(int id);
    public void Update(User employee);
    public void DeleteAll();
    public Optional<User> FindById(int id);
    public List<User> findAll();
    public User findByUsername(String name);




}
