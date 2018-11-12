package com.mckesson.validation.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mckesson.validation.exception.InvalidCharacterException;

@Service
public class CharacterValidatorService implements PasswordValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(CharacterValidatorService.class);

	private String regexPattern;
	private String invalidCharacterExceptionMessage;
	private Pattern pattern;

	@Autowired
	public CharacterValidatorService(@Value("${password.validCharacterPattern}") String regexPattern,
			@Value("${password.errorMessage.invalidCharacterExceptionMessage}") String invalidCharacterExceptionMessage) {
		this.regexPattern = regexPattern;
		this.invalidCharacterExceptionMessage = invalidCharacterExceptionMessage;
		this.pattern = Pattern.compile(this.regexPattern);
	}

	@Override
	public boolean validate(String password) throws InvalidCharacterException {
		if (!pattern.matcher(password).matches()) {
			LOGGER.warn("Password: {}, Validation: {} ", password, invalidCharacterExceptionMessage);
			throw new InvalidCharacterException(invalidCharacterExceptionMessage);
		}
		return true;
	}

}
