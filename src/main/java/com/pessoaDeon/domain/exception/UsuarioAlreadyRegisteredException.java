package com.pessoaDeon.domain.exception;

public class UsuarioAlreadyRegisteredException extends RuntimeException {
    public UsuarioAlreadyRegisteredException(String message) {
        super(message);
    }
}
