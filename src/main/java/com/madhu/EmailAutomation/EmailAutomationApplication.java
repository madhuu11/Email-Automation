package com.madhu.EmailAutomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//enable the scheduling
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmailAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailAutomationApplication.class, args);
	}

}
