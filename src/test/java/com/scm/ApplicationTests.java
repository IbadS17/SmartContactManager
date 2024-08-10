package com.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.services.EmailServices;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailServices emailServices;

	@Test
    void sendEmailTest() {
        emailServices.sendEmail("ibads66585@gmail.com", "Just Managing Some Email", "This is a test email.");
    }

}
