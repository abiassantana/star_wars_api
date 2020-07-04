package com.star_wars_resistence.star_wars_api.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class IventarioException extends RuntimeException{
	
	
	public IventarioException (String message) {
		super(message);
	}
}
