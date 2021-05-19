package com.recipe.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionHelper.class);
	
	
	@ExceptionHandler(value = { ExpiredJwtException.class })
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest req) {
		log.error("ExpiredJwtException: ", ex.getMessage());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = { SignatureClaimException.class })
	public ResponseEntity<Object> handleSignatureClaimException(SignatureClaimException ex, WebRequest req) {
		log.error("SignatureClaimException: ");
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMsg(),req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = { NoEntityFoundException.class })
	public ResponseEntity<Object> handleNoEntityFoundException(NoEntityFoundException ex, WebRequest req) {
		log.error("Exception: ", ex.getMessage());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMsg(),req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {
		log.error("Exception: ", ex.getMessage());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
