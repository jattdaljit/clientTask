package com.mckesson.validation.exception;

public class InvalidLengthException extends InvalidPasswordException {

	private static final long serialVersionUID = 1L;

	public InvalidLengthException(String message) {
		super(message);
	}
}
