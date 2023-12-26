package sookook.daybyday;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sookook.daybyday.repository.MemberRepository;

@SpringBootTest
class DaybydayApplicationTests {


	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}

}
