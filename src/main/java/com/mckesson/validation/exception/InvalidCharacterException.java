package com.mckesson.validation.exception;

public class InvalidCharacterException extends InvalidPasswordException {

	private static final long serialVersionUID = 1L;

	public InvalidCharacterException(String message) {
		super(message);
	}
}
