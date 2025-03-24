package com.example.quiz.Repo;

import com.example.quiz.Entity.User;
import com.example.quiz.Entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAnswer_Repo extends JpaRepository<UserAnswer,Integer > {

    public UserAnswer findUserAnswerByQuestionId(int question_id);

    UserAnswer findUserAnswerByQuestionIdAndUsername(Integer questionId, User username);

    List<UserAnswer> findUserAnswerByUsername(User username);

    @Query("SELECT ua.username.username from UserAnswer ua")
    List<String> getUsernames();

}
