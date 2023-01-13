package com.masaiemail.exception;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.masaiemail.exception.EmailException;
import com.masaiemail.exception.UserException;

@ControllerAdvice
public class GlobalExepctionHandller {
	
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> UserExecption(UserException e){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(e.getMessage(),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<MyErrorDetails> EmailExecption(EmailException e){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(e.getMessage(),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> globaleExecption(Exception e){
		return new ResponseEntity<MyErrorDetails>(new MyErrorDetails(e.getMessage(),LocalDate.now()), HttpStatus.BAD_GATEWAY);
	}

}
