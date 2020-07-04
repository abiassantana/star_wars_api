package com.star_wars_resistence.star_wars_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PontuacaoException extends RuntimeException{

	public PontuacaoException (String message) {
		super(message);
	}
}
