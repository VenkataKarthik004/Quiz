package com.example.Quiz;

import com.example.Quiz.Model.Response;
import com.example.Quiz.Model.Session;
import com.example.Quiz.Model.Question;
import com.example.Quiz.Repository.QuestionRepository;
import com.example.Quiz.Repository.ResponseRepository;
import com.example.Quiz.Repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ResponseRepository responseRepository;

    public Session startQuiz(Long userId) {
        Session session = new Session();
        session.setUserId(userId);
        session.setStartTime(LocalDateTime.now());
        return sessionRepository.save(session);
    }

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new IllegalStateException("No questions available");
        }
        return questions.get(new Random().nextInt(questions.size()));
    }

    public Response submitAnswer(Long sessionId, Long questionId, String selectedAnswer) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid question ID");
        }

        Question question = questionOptional.get();
        boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(selectedAnswer);

        Response response = new Response();
        response.setSessionId(sessionId);
        response.setQuestionId(questionId);
        response.setSelectedAnswer(selectedAnswer);
        response.setCorrect(isCorrect);

        return responseRepository.save(response);
    }

    public List<Response> getQuizSummary(Long sessionId) {
        return responseRepository.findBySessionId(sessionId);
    }
}
