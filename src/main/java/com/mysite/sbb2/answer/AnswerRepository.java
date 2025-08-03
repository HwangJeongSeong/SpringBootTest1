package com.mysite.sbb2.answer;

import org.springframework.data.jpa.repository.JpaRepository;

public class AnswerRepository {
    public interface AnswerRepository extends JpaRepository<Answer, Integer>{

    }
}
