package com.mckesson.validation.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mckesson.validation.exception.InvalidSequenceException;

@Service
public class SequenceValidatorService implements PasswordValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(SequenceValidatorService.class);

	private String regexPattern;
	private String invalidSequenceExceptionMessage;
	private Pattern pattern;
	private Matcher matcher;

	@Autowired
	public SequenceValidatorService(@Value("${password.sequencePattern}") String pattern,
			@Value("${password.errorMessage.invalidSequenceExceptionMessage}") String invalidSequenceExceptionMessage) {

		this.regexPattern = pattern;
		this.invalidSequenceExceptionMessage = invalidSequenceExceptionMessage;
		this.pattern = Pattern.compile(regexPattern);
	}

	@Override
	public boolean validate(String password) throws InvalidSequenceException {
		matcher = pattern.matcher(password);
		if (matcher.find()) {
			LOGGER.warn("Password: {} Validation: {}", password, invalidSequenceExceptionMessage);
			throw new InvalidSequenceException(invalidSequenceExceptionMessage);
		}
		return true;
	}

}
