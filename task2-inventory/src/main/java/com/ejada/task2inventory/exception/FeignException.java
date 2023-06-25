package com.ejada.task2inventory.exception;

@SuppressWarnings("serial")
public class FeignException extends RuntimeException {
	public FeignException(String message, Throwable cause) {
		super(message, cause);
	}
	public FeignException(String message) {
		super(message);
	}
	public FeignException( Throwable cause) {
		super(cause);
	}
}
