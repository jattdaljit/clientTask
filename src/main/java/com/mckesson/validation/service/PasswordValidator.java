package com.mckesson.validation.service;

import com.mckesson.validation.exception.InvalidPasswordException;

public interface PasswordValidator {

	boolean validate(String password) throws InvalidPasswordException;
}
