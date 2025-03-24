package com.example.quiz.Service;


import com.example.quiz.Entity.Question;
import com.example.quiz.Entity.User;
import com.example.quiz.Entity.UserAnswer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QuestionLoader2 {


    private final QuestionImpl questionImpl;
    private final UserAnswerImpl userAnswerImpl;
    private final List<Question> questionList;
    private final UserImpl userImpl;
//    private int index=0;

    public QuestionLoader2(QuestionImpl questionImpl, UserAnswerImpl userAnswerImpl,UserImpl userImpl) {
        this.questionImpl = questionImpl;
        this.userImpl = userImpl;
        this.userAnswerImpl = userAnswerImpl;

        questionList = questionImpl.findAll();
    }

    public  boolean checkSubmitted(int index, User user){

        return userAnswerImpl.findByQuestionIdAndUsername(questionList.get(index).getId(),user) != null;

    }

    public Question GetQuestion(int index){

        return questionList.get(index);

    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public HashMap<User,Double> LeaderBoard(){


       List<String> users = userAnswerImpl.findUsernames();
       HashMap<User,Double> map = new HashMap<>();

       for(String name: users){

           int score = 0;
           double percentage;

           List<UserAnswer> answers = userAnswerImpl.findByUserAnswerByUsername(userImpl.findByUsername(name));
           for(UserAnswer a: answers){

               if(a.getCorrect()){
                   score++;
               }

           }
           System.out.println("score :"+ score);
           percentage = ((double) score/questionList.size())*100;
           map.put(userImpl.findByUsername(name),percentage);
       }
         return map;
    }



}
