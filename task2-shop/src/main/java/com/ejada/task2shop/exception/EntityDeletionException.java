package com.ejada.task2shop.exception;

@SuppressWarnings("serial")
public class EntityDeletionException extends RuntimeException {
	public EntityDeletionException(String message, Throwable cause) {
		super(message, cause);
	}
	public EntityDeletionException(String message) {
		super(message);
	}
	public EntityDeletionException( Throwable cause) {
		super(cause);
	}
}
