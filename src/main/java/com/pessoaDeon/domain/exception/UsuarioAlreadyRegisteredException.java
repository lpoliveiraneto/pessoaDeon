package com.pessoaDeon.domain.exception;

public class UsuarioAlreadyRegisteredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public UsuarioAlreadyRegisteredException(String message) {
        super(message);
    }
}
