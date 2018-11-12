package com.epam.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mckesson.validation.exception.InvalidSequenceException;
import com.mckesson.validation.passwordvalidator.PasswordValidation;
import com.mckesson.validation.service.SequenceValidatorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswordValidation.class)
public class SequenceValidatorTest {

	@Autowired
	SequenceValidatorService sequenceValidator;

	@Test(expected = InvalidSequenceException.class)
	public void validate_containRepeatingSequenceOfSameCharacters() throws InvalidSequenceException {
		sequenceValidator.validate("aaaa");
	}

	@Test(expected = InvalidSequenceException.class)
	public void validate_containRepeatingSequenceOfDifferentCharacters() throws InvalidSequenceException {
		sequenceValidator.validate("pa1a1word");
	}

	@Test
	public void validate_containRepeatingSequenceOfDifferentCharactersAtDifferentPosition()
			throws InvalidSequenceException {
		assertTrue(sequenceValidator.validate("pa1worda1"));
	}

	@Test
	public void validate_containNoRepeatingCharacters() throws InvalidSequenceException {
		assertTrue(sequenceValidator.validate("pasword"));
	}

}
