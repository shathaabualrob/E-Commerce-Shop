package com.ejada.task2inventory.exception;

@SuppressWarnings("serial")
public class EntityCreationException extends RuntimeException {
	public EntityCreationException(String message, Throwable cause) {
		super(message, cause);
	}
	public EntityCreationException(String message) {
		super(message);
	}
	public EntityCreationException( Throwable cause) {
		super(cause);
	}
}
