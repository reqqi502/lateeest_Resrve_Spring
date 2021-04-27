package com.basmaonline.exceptions;



import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.basmaonline.ws.Entity.UserEntity;
import com.basmaonline.ws.responses.MessageError;

import jdk.jshell.spi.ExecutionControl.UserException;

@ControllerAdvice
public class appExceptionHandler {
	@ExceptionHandler(value= {UserException.class}) // Class Exception
	public ResponseEntity<Object> HandleUserException(UserException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value= Exception.class) // Class Exception
	public ResponseEntity<Object> HandleOtherException(Exception ex, WebRequest request) {
		MessageError errormessage = new MessageError(new Date() , ex.getMessage());
		return new ResponseEntity<>(errormessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
