package com.example.Quiz.Repository;

import com.example.Quiz.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository  extends JpaRepository<Session,Long> {
}
