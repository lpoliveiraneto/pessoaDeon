package com.pessoaDeon.config.validacao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pessoaDeon.domain.exception.EnderecoNotFoundException;
import com.pessoaDeon.domain.exception.EnviaBoSigmaException;
import com.pessoaDeon.domain.exception.PessoaAlreadyRegisteredException;
import com.pessoaDeon.domain.exception.UsuarioAlreadyRegisteredException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> tratarErro404(NoSuchElementException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UsuarioAlreadyRegisteredException.class)
    public ResponseEntity<?> tratarError409(UsuarioAlreadyRegisteredException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DadosErroStatus(HttpStatus.CONFLICT.toString(), "Usuario já consta na Base de dados"));
    }

    @ExceptionHandler(PessoaAlreadyRegisteredException.class)
    public ResponseEntity<?> tratarError409(PessoaAlreadyRegisteredException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DadosErroStatus(HttpStatus.CONFLICT.toString(), "Pessoa já consta na Base de dados"));
    }
    
    @ExceptionHandler(EnderecoNotFoundException.class)
    public ResponseEntity<?> tratarError409(EnderecoNotFoundException ex){
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(new DadosErroStatus(HttpStatus.CONFLICT.toString(), ex.getMessage())); 
    }

    @ExceptionHandler(EnviaBoSigmaException.class)
    public ResponseEntity<?> tratarError409(EnviaBoSigmaException ex){
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DadosErroStatus("Erro de comunicação com a API de Integração DEON/SIGMA", ex.getMessage())); 
    }
    
    private record DadosErroValidacao(String campo, String message){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    private record DadosErroStatus(String status, String message){}

}
