package com.pessoaDeon.domain.exception;

public class BoNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public BoNotFoundException(String exception){
        super(exception);
    }
}
