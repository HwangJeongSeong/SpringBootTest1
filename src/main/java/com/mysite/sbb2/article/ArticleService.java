package com.mysite.sbb2.article;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//생성자 주입
@RequiredArgsConstructor
//서비스를 만들기위한 어노테이션
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        //findAll 메서드를 사용하여 질문 목록 데이터 questionList를 생성, Model객체에 저장.
        //requireArgsConstructor으로 qustionRepository 객체를 자동 주입했다
        return this.articleRepository.findAll();
    }
}
