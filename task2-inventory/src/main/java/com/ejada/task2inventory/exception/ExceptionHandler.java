package com.ejada.task2inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NotFoundException e){
		// create a StudentErrorResponse object
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(EntityCreationException  e){
		// create a StudentErrorResponse object
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(FeignException e){
		// create a StudentErrorResponse object
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NotEnoughBalancException e){
		// create a StudentErrorResponse object
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(AccessDeniedException  e){
		// create a StudentErrorResponse object
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		// create a StudentErrorResponse object
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
