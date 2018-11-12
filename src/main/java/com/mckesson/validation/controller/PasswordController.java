package com.mckesson.validation.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mckesson.validation.exception.InvalidPasswordException;
import com.mckesson.validation.service.PasswordValidator;

@Controller
public class PasswordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

	@Autowired
	private List<PasswordValidator> passwordValidators = new ArrayList<>();

	@RequestMapping(value = "/validatePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> validatePassword(
			@RequestParam(value = "password", required = false) String password) {
		String message = null;
		HttpStatus status = null;

		if (password == null) {
			message = "password cannot be null";
			status = HttpStatus.BAD_REQUEST;
			LOGGER.info("Message: {} Status: {}", message, status);
			return new ResponseEntity<>(message, status);
		} else {
			return applyValidations(password);
		}
	}

	private ResponseEntity<String> applyValidations(String password) {
		String message = null;
		HttpStatus status = null;
		try {
			for (PasswordValidator validator : passwordValidators) {
				validator.validate(password);
			}
			message = "password is valid";
			status = HttpStatus.OK;
		} catch (InvalidPasswordException exception) {
			message = exception.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		LOGGER.info("Message: {} Status: {}", message, status);
		return new ResponseEntity<>(message, status);
	}
}
