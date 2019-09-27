package com.idexcel.sohamadminservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.idexcel.sohamadminservice.exception.LenderAlreadyExistsException;
import com.idexcel.sohamadminservice.exception.LenderNotFoundException;

@ControllerAdvice
public class LenderRestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<LenderErrorResponse> handleException(LenderNotFoundException exc)
	{
		LenderErrorResponse error=new LenderErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("Lender not Found");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<LenderErrorResponse> handleException(LenderAlreadyExistsException exc)
	{
		LenderErrorResponse error=new LenderErrorResponse();
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage("Lender Already Exists");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.CONFLICT);
	}

}
