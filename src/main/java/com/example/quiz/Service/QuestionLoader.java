package com.example.quiz.Service;

import com.example.quiz.Entity.Question;

import com.example.quiz.Entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionLoader {

    private static int index = 0;
    private static List<Question> questionList;
    private static List<Boolean> SubmitStatus;

    public static void LoadQuestions(QuestionImpl service, UserAnswerImpl userAnswerImpl){

        if(questionList==null) {
           //load only the first time
            questionList = service.findAll();
        }

        System.out.println("entering");
        if (SubmitStatus==null){

            SubmitStatus = new ArrayList<>();
           for (Question q : questionList){
               if(userAnswerImpl.findByQuestionId(q.getId())!=null) {
                   SubmitStatus.add(true);
                   System.out.println("added .....");
               }
           }
        }
    }

    public static boolean checkSubmitted(UserAnswerImpl service){

        return service.findByQuestionId(questionList.get(index).getId()) != null;

    }

    public static boolean checkSubmitted(){

        return SubmitStatus.get(index);
    }



    public static Question GetQuestion(){

        return questionList.get(index);

    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        QuestionLoader.index = index;
    }

    public static List<Question> getQuestionList() {
        return questionList;
    }


}
