package com.pessoaDeon.api.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pessoaDeon.domain.exception.EnderecoNotFoundException;
import com.pessoaDeon.domain.exception.LogradouroNotFoundException;
import com.pessoaDeon.domain.exception.PessoaNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<Object> handlerPessoaNotFoundException(PessoaNotFoundException exception, WebRequest request){
        Problema problema = new Problema(LocalDateTime.now(), exception.getMessage(), exception.toString());
        HttpStatus status = HttpStatus.CONFLICT;

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(LogradouroNotFoundException.class)
    public ResponseEntity<Object> handlerLogradouroNotFoundException(LogradouroNotFoundException exception, WebRequest request){
        Problema problema = new Problema(LocalDateTime.now(), exception.getMessage(), exception.toString());
        HttpStatus status = HttpStatus.CONFLICT;

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }
    
    @ExceptionHandler(EnderecoNotFoundException.class)
    public ResponseEntity<?> handlerEnderecoNotFoundException(EnderecoNotFoundException enderecoException, WebRequest request){
    	Problema problemaEndereco = new Problema(LocalDateTime.now(), enderecoException.getMessage(), enderecoException.toString());
    	HttpStatus status = HttpStatus.CONFLICT;
    	return handleExceptionInternal(enderecoException, problemaEndereco, new HttpHeaders(), status, request);
    }
}
