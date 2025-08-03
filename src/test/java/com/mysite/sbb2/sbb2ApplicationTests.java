package com.mysite.sbb2;

import com.mysite.sbb2.answer.Answer;
import com.mysite.sbb2.answer.AnswerRepository;
import com.mysite.sbb2.question.Question;
import com.mysite.sbb2.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class sbb2ApplicationTests {


	// @Autowired 애너테이션을 통해 스프링의 ‘의존성 주입(DI)’이라는 기능을 사용하여 QuestionRepository의 객체를 주입했다.
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("sbb가 뭐임?");
		q1.setContent("sbb에 알고 싶음");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링 부트 모델 질문임");
		q2.setContent("Id는 자동으로 생성됨?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

	@Test
	void testJpa2() {
		//findAll은 sql에서 select * from question; 과 같다.
		List<Question> all = this.questionRepository.findAll();
		//데이터 사이즈가 2개인지 확인하는 메서드. assertEquals(기댓값, 실젯값)
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 뭐임?", q.getSubject());
	}

	@Test
	void testJpa3() {
		//findById의 리턴 타입은 Question이 아닌 Optional임
		//findById로 호출한 값이 존재할 수도 있고, 존재하지 않을 수도 있어서 리턴 타입으로 Optional이 사용된 것
		Optional<Question> oq = this.questionRepository.findById(1);
		//isPresent()를 통해 값이 존재한다는 것
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 뭐임?", q.getSubject());
		}
	}

	@Test
	void testJpa4() {
		Question q = this.questionRepository.findBySubject("sbb가 뭐임?");
		assertEquals(1, q.getId());
	}

	@Test
	void testJpa5() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"sbb가 뭐임?", "sbb에 알고 싶음");
		assertEquals(1, q.getId());
	}

	@Test
	void testJpa6() {
		//sbb%	'sbb'로 시작하는 문자열
		//%sbb	'sbb'로 끝나는 문자열
		//%sbb%	'sbb'를 포함하는 문자열
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 뭐임?", q.getSubject());
	}

	@Test
	void testJpa7() {
		Optional<Question> oq = this.questionRepository.findById(1);
		//assertTrue()는 괄호 안의 값이 true(참) 인지를 테스트한다. oq.isPresent()가 false를 리턴하면 오류가 발생하고 테스트가 종료된다.
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}
	@Test
	void testJpa8() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}

	@Test
	void testJpa9() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}

	@Test
	void testJpa10() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}

	@Transactional
	@Test
	void testJpa11() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}



