package com.example.quiz.Service;

import com.example.quiz.Entity.User;
import com.example.quiz.Entity.UserAnswer;
import com.example.quiz.Repo.UserAnswer_Repo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAnswerImpl implements UserAnswerService {

    private final UserAnswer_Repo repo;

    public UserAnswerImpl(UserAnswer_Repo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void Save(UserAnswer userAnswer) {
        repo.save(userAnswer);
    }

    @Override
    @Transactional
    public void DeleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void Update(UserAnswer userAnswer) {
      repo.save(userAnswer);
    }

    @Override
    @Transactional
    public void DeleteAll() {
        repo.deleteAll();
    }

    @Override
    public Optional<UserAnswer> FindById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<UserAnswer> findAll() {
        return repo.findAll();
    }

    @Override
    public UserAnswer findByQuestionId(int id) {
        return repo.findUserAnswerByQuestionId(id);
    }

    @Override
    public UserAnswer findByQuestionIdAndUsername(Integer qid, User user) {
        return repo.findUserAnswerByQuestionIdAndUsername(qid,user);
    }

    @Override
    public List<UserAnswer> findByUserAnswerByUsername(User user) {
        return repo.findUserAnswerByUsername(user);
    }

    @Override
    public List<String> findUsernames() {
        return repo.getUsernames();
    }


}
