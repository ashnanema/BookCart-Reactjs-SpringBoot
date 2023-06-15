package com.bookcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException{
	
	 private String message;
	    public MethodNotAllowedException(String message) {
	        super(message);
	        this.message = message;
	    }
	   

}
