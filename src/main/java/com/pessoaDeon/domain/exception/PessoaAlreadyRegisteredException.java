package com.pessoaDeon.domain.exception;

public class PessoaAlreadyRegisteredException extends RuntimeException{

    public PessoaAlreadyRegisteredException(String message){
        super(message);
    }
}
