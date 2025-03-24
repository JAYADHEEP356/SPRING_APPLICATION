package com.example.quiz.Repo;

import com.example.quiz.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Question_Repo extends JpaRepository<Question,Integer > {
}
