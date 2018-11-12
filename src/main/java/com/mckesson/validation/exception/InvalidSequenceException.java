package com.mckesson.validation.exception;

public class InvalidSequenceException extends InvalidPasswordException {

	private static final long serialVersionUID = 1L;

	public InvalidSequenceException(String message) {
		super(message);
	}
}
