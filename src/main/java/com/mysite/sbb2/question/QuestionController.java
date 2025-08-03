package com.mysite.sbb2.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//final이 붙은 생성자를 자동으로 만들어 주는 역할
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
    //매개변수로 Model을 지정하면 객체가 자동으로 생성된다.
    //Model객체는 따로 생성필요X,
    public String list(Model model) {
        //requireArgsConstructor으로 qustionRepository 객체를 자동 주입했다
        //findAll 메서드를 사용하여 질문 목록 데이터 questionList를 생성, Model객체에 저장.
        //Model객체는 클래스와 템플릿을 연결.
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        //템플릿 파일 이름을 리턴
        return "question_list";
    }
}
