package com.example.quiz.Controllers;

import com.example.quiz.Entity.*;
import com.example.quiz.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Controller
public class AppController {

    private final UserImpl userService;


    private final QuestionImpl questionImpl;
    private final OptionImpl optionImpl;
    private final UserAnswerImpl userAnswerImpl;
    private final AuthorityImpl authorityImpl;
    private final QuestionLoader2 questionLoader2;
    private HashMap<String, Integer> map;

    public AppController(UserImpl userService, QuestionImpl questionImpl, OptionImpl optionImpl, UserAnswerImpl userAnswerImpl, AuthorityImpl authorityImpl, QuestionLoader2 questionLoader2) {
        this.userService = userService;
        this.questionImpl = questionImpl;
        this.optionImpl = optionImpl;
        this.userAnswerImpl = userAnswerImpl;
        this.authorityImpl = authorityImpl;
        this.questionLoader2 = questionLoader2;
        map = new HashMap<>();
    }

    @RequestMapping("/login")
    public String Login(HttpSession session) {


        if (map.containsKey(SecurityContextHolder.getContext().getAuthentication().getName())) {

            SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping("/signUp")
    public String SignIn(Model model) {


        model.addAttribute("user", new User());
        return "signUp";

    }

    @GetMapping("/success")
    public String Success() {
        return "success";
    }

    @PostMapping("/AddUser")
    public String AddUser(@ModelAttribute("user") User user) {

        userService.Save(user);
        Authority authority = new Authority();
        authority.setUsername(user);
        authority.setAuthority("ROLE_USER");
        authorityImpl.Save(authority);
        return "login";
    }

    @PostMapping("/SubmitQuiz")
    public String SubmitQuiz(@RequestParam("selectedOption") String selectedOption,
                             @RequestParam("malpractice") boolean malpractice,
                             Model model) {

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        int index = map.get(user.getUsername());
        if (index < questionLoader2.getQuestionList().size()) {

            Option option = optionImpl.FindByOptionText(selectedOption);
            System.out.println(option.toString());
            Question question = questionImpl.FindById(option.getQuestion().getId()).orElse(null);
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setTime(Instant.now());
            userAnswer.setQuestion(question);
            userAnswer.setUsername(user);
            userAnswer.setSelectedOption(option);
            userAnswer.setMalpractice(malpractice);

            assert question != null;
            userAnswer.setCorrect(Objects.equals(option.getId(), question.getCorrectOptionId()));

            userAnswerImpl.Save(userAnswer);
            map.put(user.getUsername(), index + 1);
            if (questionLoader2.getQuestionList().size() == map.get(user.getUsername())) {

                if(user.getEndTime()==null){
                    user.setEndTime(Instant.now());
                    userService.Update(user);
                    System.out.println("Start Time: " + user.getStartTime());
                    System.out.println("End Time: " + user.getEndTime());

                    user.setTotalTime(Duration.between(user.getStartTime(),user.getEndTime()).getSeconds());
                    userService.Update(user);
                }
                HashMap<User, Double> map2 = questionLoader2.LeaderBoard();
                model.addAttribute("username", userAnswer.getUsername().getUsername());
                model.addAttribute("percentage", map2.get(userAnswer.getUsername()));
                map.remove(user.getUsername());
                map.put(user.getUsername(),0);

                return "success";
            }

            return "redirect:/";
        }

        return "success";
    }

    @GetMapping("goPrevious")
    public String GoPrevious() {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int index = map.get(name);

        if (index > 0) {
            map.put(name, index - 1);
            return "redirect:/";
        }

        return "redirect:/";
    }

    @GetMapping("goNext")
    public String GoNext() {


        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int index = map.get(name);
        if (index < questionLoader2.getQuestionList().size() - 1) {

            map.put(name, index + 1);
            return "redirect:/";
        }
        return "redirect:/";
    }


    @GetMapping("/")
    public String quizHome(Model model) {


         User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
         model.addAttribute("isTimeUp",user.getTimeup());
         if (user.getStartTime()==null){
             user.setStartTime(Instant.now());
             userService.Update(user);
             System.out.println("user login time"+user.getStartTime());
         }

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("user now: " + name);
        if (!map.containsKey(name)) {

            map.put(name, 0);
        }

        Question question = questionLoader2.GetQuestion(map.get(name));
        List<Option> options = optionImpl.findByQuestion(question);
        model.addAttribute("question", question);
        model.addAttribute("options", options);
        if (questionLoader2.checkSubmitted(map.get(name), userService.findByUsername(name))) {
            model.addAttribute("isSubmitted", true);
        } else {
            model.addAttribute("isSubmitted", false);
        }
        return "quizHome";
    }

    @GetMapping("/quiz-time-up")
    public String getTimeUP(){
        return "quizTimeUp";
    }

    @GetMapping("/leaderBoard")
    public String LeaderBoard(Model model){

        HashMap<User,Double> scoreMap = questionLoader2.LeaderBoard();
        System.out.println("first map: ");

        TreeMap<User, Double> userScores = new TreeMap<>((u1, u2) -> {
            int scoreCompare = Double.compare(scoreMap.get(u2), scoreMap.get(u1)); // Higher score first
            return (scoreCompare != 0) ? scoreCompare : Long.compare(u1.getTotalTime(), u2.getTotalTime()); // Lower totalTime first
        });

        userScores.putAll(scoreMap);
        System.out.println(userScores);
        model.addAttribute("map",userScores);
        return "leaderboard";
    }

}
