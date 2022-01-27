package com.mentorship.demo;

import com.mentorship.demo.controller.AbiturientController;
import com.mentorship.demo.controller.AuthenticationController;
import com.mentorship.demo.dto.AbiturientDto;
import com.mentorship.demo.dto.AuthRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private AuthenticationController authenticationController;
	@Autowired
	private AbiturientController abiturientController;

	@Test
	public void authTest() {
		abiturientController.createAbiturient(AbiturientDto.builder()
				.firstName("Dave")
				.lastName("Matthews")
				.phoneNumber("380682079553")
				.password("$2a$10$TrSlHkrApIxexkkl.SEpj.LlrEtJmEAxsNw/4SegDLghIsDlesh/2")
				.build());
		authenticationController.login(new AuthRequestDto("Dave", "1592000"));
	}

}
