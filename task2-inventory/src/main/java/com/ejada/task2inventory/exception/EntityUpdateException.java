package com.ejada.task2inventory.exception;

@SuppressWarnings("serial")
public class EntityUpdateException extends RuntimeException {
	public EntityUpdateException(String message, Throwable cause) {
		super(message, cause);
	}
	public EntityUpdateException(String message) {
		super(message);
	}
	public EntityUpdateException( Throwable cause) {
		super(cause);
	}
}
