package com.example.Quiz.Repository;

import com.example.Quiz.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository  extends JpaRepository<Question,Long> {
}
