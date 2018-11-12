package com.epam.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mckesson.validation.exception.InvalidLengthException;
import com.mckesson.validation.passwordvalidator.PasswordValidation;
import com.mckesson.validation.service.LengthValidatorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswordValidation.class)
public class LengthValidatorTest {

	@Autowired
	private LengthValidatorService lengthValidator;

	@Test(expected = InvalidLengthException.class)
	public void validate_lengthIsLessThan5() throws InvalidLengthException {
		lengthValidator.validate("pasd");
	}

	@Test(expected = InvalidLengthException.class)
	public void validate_lengthIsGreaterThan12() throws InvalidLengthException {
		lengthValidator.validate("paswordpasword");
	}

	@Test
	public void validate_lengthIsEqualTo5() throws InvalidLengthException {
		assertTrue(lengthValidator.validate("paswd"));
	}

	@Test
	public void validate_lengthIsEqualTo12() throws InvalidLengthException {
		assertTrue(lengthValidator.validate("paswordpaswo"));
	}

	@Test
	public void validate_lengthIsInBetween5And12() throws InvalidLengthException {
		assertTrue(lengthValidator.validate("pasword"));
	}

}
