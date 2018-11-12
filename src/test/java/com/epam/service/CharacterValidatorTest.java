package com.epam.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mckesson.validation.exception.InvalidCharacterException;
import com.mckesson.validation.passwordvalidator.PasswordValidation;
import com.mckesson.validation.service.CharacterValidatorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswordValidation.class)
public class CharacterValidatorTest {

	@Autowired
	private CharacterValidatorService characterValidator;

	@Test(expected = InvalidCharacterException.class)
	public void validate_containsUpperCaseLetter() throws InvalidCharacterException {
		characterValidator.validate("paswordD");
	}

	@Test(expected = InvalidCharacterException.class)
	public void validate_mustContainAtleastOneNumericDigit() throws InvalidCharacterException {
		characterValidator.validate("paswordpas");
	}

	@Test(expected = InvalidCharacterException.class)
	public void validate_mustContainAtleastLowerCaseLetter() throws InvalidCharacterException {
		characterValidator.validate("11111111");
	}

	@Test(expected = InvalidCharacterException.class)
	public void validate_containsSpecialCharacter() throws InvalidCharacterException {
		characterValidator.validate("pasword123@");
	}

	@Test(expected = InvalidCharacterException.class)
	public void validate_containsEmptyString() throws InvalidCharacterException {
		characterValidator.validate("");
	}

	@Test
	public void validate_containsMixtureofLowerCaseLettersAndDigits() throws InvalidCharacterException {
		assertTrue(characterValidator.validate("pasword123"));
	}
}
