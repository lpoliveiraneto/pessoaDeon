package com.pessoaDeon.domain.exception;

public class PessoaNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public PessoaNotFoundException(String exception){
        super(exception);
    }
}
