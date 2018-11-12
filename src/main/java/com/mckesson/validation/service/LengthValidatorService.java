package com.mckesson.validation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mckesson.validation.exception.InvalidLengthException;

@Service
public class LengthValidatorService implements PasswordValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(LengthValidatorService.class);

	private int minimumLength;
	private int maximumLength;
	private String invalidLengthExceptionMessage;

	@Autowired
	public LengthValidatorService(@Value("${password.minimumLength}") int minimumLength,
			@Value("${password.maximumLength}") int maximumLength,
			@Value("${password.errorMessage.invalidLengthExceptionMessage}") String invalidLengthExceptionMessage) {

		this.minimumLength = minimumLength;
		this.maximumLength = maximumLength;
		this.invalidLengthExceptionMessage = invalidLengthExceptionMessage;

	}

	@Override
	public boolean validate(String password) throws InvalidLengthException {
		int passwordLength = password.length();
		validateLength(passwordLength);
		return true;
	}

	private void validateLength(int passwordLength) throws InvalidLengthException {
		if (!(passwordLength >= minimumLength && passwordLength <= maximumLength)) {
			LOGGER.warn("Validation: {} ", invalidLengthExceptionMessage);
			throw new InvalidLengthException(invalidLengthExceptionMessage);
		}
	}

}
