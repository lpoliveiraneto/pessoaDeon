package com.pessoaDeon.config.validacao;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.pessoaDeon.domain.exception.PessoaAlreadyRegisteredException;
import com.pessoaDeon.domain.exception.UsuarioAlreadyRegisteredException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarErro404(NoSuchElementException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UsuarioAlreadyRegisteredException.class)
    public ResponseEntity tratarError409(UsuarioAlreadyRegisteredException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DadosErroStatus(HttpStatus.CONFLICT.toString(), "Usuario já consta na Base de dados"));
    }

    @ExceptionHandler(PessoaAlreadyRegisteredException.class)
    public ResponseEntity tratarError409(PessoaAlreadyRegisteredException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DadosErroStatus(HttpStatus.CONFLICT.toString(), "Pessoa já consta na Base de dados"));
    }
    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    private record DadosErroStatus(String status, String mensagem){}

}
