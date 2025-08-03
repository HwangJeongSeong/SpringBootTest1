package com.mysite.sbb2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "안녕하세요 sbb에 오신것을 환영합니다";
    }
    
    //root메서드를 추가 하고 /url을 매핑, redirect/question/list는 /question/list URL로 페이지를 리다이렉트 하라는 명령
    //localhost:8082 페이지 접속하면 root메서드가 실행돼 /question/list로 주소가 바뀐다
    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}
