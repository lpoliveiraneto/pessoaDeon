package com.pessoaDeon.domain.exception;

public class LogradouroNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public LogradouroNotFoundException(String exception){
        super(exception);
    }
}
