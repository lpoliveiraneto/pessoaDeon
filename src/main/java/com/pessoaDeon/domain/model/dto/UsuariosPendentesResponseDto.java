package com.pessoaDeon.domain.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosPendentesResponseDto {

    private Integer idUsuario;
    
    private String nome;
    
    private String email;
    
    private String cpf;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
    
    private Integer idPessoa;

}
