package com.example.Quiz.Repository;

import com.example.Quiz.Model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {

    List<Response> findBySessionId(Long sessionId);
}
