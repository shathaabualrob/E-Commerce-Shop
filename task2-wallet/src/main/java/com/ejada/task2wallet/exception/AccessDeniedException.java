package com.ejada.task2wallet.exception;

@SuppressWarnings("serial")
public class AccessDeniedException extends RuntimeException {
	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
	public AccessDeniedException(String message) {
		super(message);
	}
	public AccessDeniedException( Throwable cause) {
		super(cause);
	}
}
