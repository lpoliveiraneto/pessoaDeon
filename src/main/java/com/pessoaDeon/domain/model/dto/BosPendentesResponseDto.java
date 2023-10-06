package com.pessoaDeon.domain.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BosPendentesResponseDto {

    private Integer idBo;
    
    private String natureza;
    
    private String nome;
    
    private String protocolo;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDoRegistro;

}
