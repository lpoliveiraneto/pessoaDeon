package com.pessoaDeon.api.handler;

import com.pessoaDeon.domain.exception.LogradouroNotFoundException;
import com.pessoaDeon.domain.exception.PessoaNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<Object> handlerPessoaNotFoundException(PessoaNotFoundException exception, WebRequest request){
        Problema problema = new Problema(LocalDateTime.now(), exception.getMessage(), exception.toString());
        HttpStatus status = HttpStatus.NOT_FOUND;

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(LogradouroNotFoundException.class)
    public ResponseEntity<Object> handlerLogradouroNotFoundException(LogradouroNotFoundException exception, WebRequest request){
        Problema problema = new Problema(LocalDateTime.now(), exception.getMessage(), exception.toString());
        HttpStatus status = HttpStatus.NOT_FOUND;

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }
}
