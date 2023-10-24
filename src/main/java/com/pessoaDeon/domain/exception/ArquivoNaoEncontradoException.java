package com.pessoaDeon.domain.exception;

import java.io.IOException;

public class ArquivoNaoEncontradoException extends RuntimeException{

    public ArquivoNaoEncontradoException(String exception, IOException e){
        super(exception);
    }
}
