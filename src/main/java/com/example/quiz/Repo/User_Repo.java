package com.example.quiz.Repo;

import com.example.quiz.Entity.Option;
import com.example.quiz.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_Repo extends JpaRepository<User,Integer > {

    User findUserByUsername(String username);
}
