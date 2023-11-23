package com.pessoaDeon.domain.exception;

import java.io.IOException;

public class ArquivoNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public ArquivoNaoEncontradoException(String exception, IOException e){
        super(exception);
    }
}
