package com.ejada.task2shop.exception;

@SuppressWarnings("serial")
public class NotEnoughBalancException extends RuntimeException {
	public NotEnoughBalancException(String message, Throwable cause) {
		super(message, cause);
	}
	public NotEnoughBalancException(String message) {
		super(message);
	}
	public NotEnoughBalancException( Throwable cause) {
		super(cause);
	}
}
