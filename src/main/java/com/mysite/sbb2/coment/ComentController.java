package com.mysite.sbb2.coment;

import com.mysite.sbb2.article.Article;
import com.mysite.sbb2.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/coment")
public class ComentController {

    private final ArticleService articleService;
    private final ComentService comentService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        Article article = this.articleService.getArticle(id);
        this.comentService.create(article, content);
        return String.format("redirect:/article/detail/%s", id);
    }
}
