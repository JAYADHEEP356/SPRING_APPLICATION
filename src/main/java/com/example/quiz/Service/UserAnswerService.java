package com.example.quiz.Service;

import com.example.quiz.Entity.User;
import com.example.quiz.Entity.UserAnswer;

import java.util.List;
import java.util.Optional;

public interface UserAnswerService {
    public void Save(UserAnswer userAnswer);
    public void DeleteById(int id);
    public void Update(UserAnswer userAnswer);
    public void DeleteAll();
    public Optional<UserAnswer> FindById(int id);
    public List<UserAnswer> findAll();
    public UserAnswer findByQuestionId(int id);
    public UserAnswer findByQuestionIdAndUsername(Integer qid, User user);
    public List<UserAnswer> findByUserAnswerByUsername(User user);
    public List<String> findUsernames();


}
