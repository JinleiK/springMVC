package com.jinlei.spring.test.springMVC.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(DataAccessException.class)
	public String HandleDatabaseException(DataAccessException ex) {
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String HandleAccessDeniedException(AccessDeniedException ex) {
		return "denied";
	}
}
