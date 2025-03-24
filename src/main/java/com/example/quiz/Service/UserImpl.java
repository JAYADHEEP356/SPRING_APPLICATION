package com.example.quiz.Service;


import com.example.quiz.Entity.User;
import com.example.quiz.Repo.User_Repo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService{
    private final User_Repo repo;


    public UserImpl(User_Repo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void Save(User user) {

        user.setEnabled(true); //service class for writing business logics
        user.setPassword("{noop}"+user.getPassword());
        repo.save(user);
    }

    @Override
    @Transactional
    public void DeleteById(int id) {

        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void Update(User user) {

        repo.save(user);
    }

    @Override
    public void DeleteAll() {
        repo.deleteAll();
    }

    @Override
    public Optional<User> FindById(int id) {

        return repo.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findByUsername(String name) {
         return repo.findUserByUsername(name);
    }

}
