package com.mysite.sbb2.question;

import org.springframework.data.jpa.repository.JpaRepository;

public class QuestionRepository {
    public interface QuestionRepository extends JpaRepository<Question, Integer> {

    }
}
