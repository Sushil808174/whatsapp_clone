package com.skumar.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException exp, WebRequest req){
		ErrorDetails err = new ErrorDetails();
		err.setMessage(exp.getMessage());
		err.setLocalDate(LocalDate.now());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MessageException.class)
	public ResponseEntity<ErrorDetails> messageExceptionHandler(MessageException exp, WebRequest req){
		ErrorDetails err = new ErrorDetails();
		err.setMessage(exp.getMessage());
		err.setLocalDate(LocalDate.now());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exp, WebRequest req){
		ErrorDetails err = new ErrorDetails();
		err.setMessage(exp.getMessage());
		err.setLocalDate(LocalDate.now());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHanderFoundExceptionHandler(NoHandlerFoundException exp, WebRequest req){
		ErrorDetails err = new ErrorDetails();
		err.setMessage(exp.getMessage());
		err.setLocalDate(LocalDate.now());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception exp, WebRequest req){
		ErrorDetails err = new ErrorDetails();
		err.setMessage(exp.getMessage());
		err.setLocalDate(LocalDate.now());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
}
