package com.mysite.sbb2.article;

import com.mysite.sbb2.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Article getArticle(Integer id) {
        return this.articleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("article not found"));
    }

    public void create(String subject, String content) {
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }
}
