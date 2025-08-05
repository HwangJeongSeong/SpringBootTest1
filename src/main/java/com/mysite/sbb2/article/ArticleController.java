package com.mysite.sbb2.article;

import com.mysite.sbb2.coment.ComentForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
//final이 붙은 생성자를 자동으로 만들어 주는 역할
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    //매개변수로 Model을 지정하면 객체가 자동으로 생성된다.
    //Model객체는 따로 생성필요X,
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        //Model객체는 클래스와 템플릿을 연결.
        //requireArgsConstructor으로 qustionService 객체를 자동 주입했다
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        //템플릿 파일 이름을 리턴
        return "article_list";
    }

    //html에서 th:href속성을 이용하기 위해 매핑
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, ComentForm comentForm) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @GetMapping("/create")
    public String articleCreate(ArticleForm articleForm) {
        return "article_form";
    }

    @PostMapping("/create")
    public String articleCreate(@Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }
        this.articleService.create(articleForm.getSubject(), articleForm.getContent());
        return "redirect:/article/list";
    }
}
