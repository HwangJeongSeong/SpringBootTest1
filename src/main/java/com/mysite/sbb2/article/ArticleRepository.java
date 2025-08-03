package com.mysite.sbb2.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    //findBySbject메서드를 기본적으로 제공하지 않아 메서드 추가
    //JPA에 리포지터리의 메서드명을 분석하여 쿼리를 만들고 실행하는 기능이 있기 때문에 메서드 선언만 하고 사용가능
    Article findBySubject(String subject);
    Article findBySubjectAndContent(String subject, String content);
    //Subject 열에서 문자열 ‘subject’와 같은 문자열을 포함한 데이터를 조회
    List<Article> findBySubjectLike(String subject);
}

