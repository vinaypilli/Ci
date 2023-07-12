package com.teamcomputers.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.teamcomputers.message.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handling specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
		ErrorResponse errorDetails = 
				new ErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(), "failed");
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> conflictExceptionHandling(ConflictException exception, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(404,exception.getMessage(), "failed");
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> unauthorizedExceptionHandling(UnauthorizedException exception, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),exception.getMessage(), "failed");
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
//	private int code;
//	private String Message;
//	private Date date;
//	private String status;
	
	// handling global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandling(Exception exception, WebRequest request){
		ErrorResponse errorDetails = 
				new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), "failed");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
