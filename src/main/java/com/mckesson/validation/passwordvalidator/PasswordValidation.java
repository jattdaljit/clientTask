package com.mckesson.validation.passwordvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class PasswordValidation {

	public static void main(String[] args) {
		SpringApplication.run(PasswordValidation.class, args);
	}
}
