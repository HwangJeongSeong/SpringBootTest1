package com.mysite.sbb2.question;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//생성자 주입
@RequiredArgsConstructor
//서비스를 만들기위한 어노테이션
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
}
