package com.pessoaDeon.domain.exception;

public class PessoaAlreadyRegisteredException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public PessoaAlreadyRegisteredException(String message){
        super(message);
    }
}
