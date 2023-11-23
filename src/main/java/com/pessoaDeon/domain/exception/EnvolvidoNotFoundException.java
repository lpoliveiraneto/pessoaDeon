package com.pessoaDeon.domain.exception;

public class EnvolvidoNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public EnvolvidoNotFoundException(String exception){
        super(exception);
    }
}
