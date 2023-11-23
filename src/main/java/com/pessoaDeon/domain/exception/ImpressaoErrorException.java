package com.pessoaDeon.domain.exception;

public class ImpressaoErrorException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public ImpressaoErrorException(String exception){
        super(exception);
    }
}
