package com.example.demo.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHnadler {

	 @ExceptionHandler(UserException.class)
	public ResponseEntity<MyError> ErrorResponsehandleException(UserException ex){
	  return new ResponseEntity<MyError>(new MyError(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}
	 

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> validationVolationHandler(MethodArgumentNotValidException ce, WebRequest req) {

		MyError err = new MyError();
		
		  Map<String, String> errors = new HashMap<>();
		    ce.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
	        
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(errors.toString());

		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);

	}
}
