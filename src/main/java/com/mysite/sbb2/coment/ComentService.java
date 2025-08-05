package com.mysite.sbb2.coment;

import com.mysite.sbb2.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ComentService {
    private final ComentRepository comentRepository;

    public void create (Article article, String content) {
        Coment coment = new Coment();
        coment.setContent(content);
        coment.setCreateDate(LocalDateTime.now());
        coment.setArticle(article);
        this.comentRepository.save(coment);
    }
}
