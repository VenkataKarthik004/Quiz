package com.example.Quiz;

import com.example.Quiz.Model.Question;
import com.example.Quiz.Model.Response;
import com.example.Quiz.Model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public Session startQuiz(@RequestParam Long userId) {
        return quizService.startQuiz(userId);
    }

    @GetMapping("/question/{sessionId}")
    public Question getRandomQuestion(@PathVariable Long sessionId) {
        return quizService.getRandomQuestion();
    }

    @PostMapping("/answer/{sessionId}")
    public Response submitAnswer(
            @PathVariable Long sessionId,
            @RequestParam Long questionId,
            @RequestParam String selectedAnswer) {
        return quizService.submitAnswer(sessionId, questionId, selectedAnswer);
    }

    @GetMapping("/summary/{sessionId}")
    public List<Response> getQuizSummary(@PathVariable Long sessionId) {
        return quizService.getQuizSummary(sessionId);
    }
}
