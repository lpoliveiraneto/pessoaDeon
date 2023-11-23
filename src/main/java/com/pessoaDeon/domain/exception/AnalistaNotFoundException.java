package com.pessoaDeon.domain.exception;

public class AnalistaNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public AnalistaNotFoundException(String exception){
        super(exception);
    }
}
