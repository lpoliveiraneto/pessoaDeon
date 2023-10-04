package com.pessoaDeon.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BosPendentesResponseDto {

    private Integer idBo;
    private String natureza;
    private String nome;
    private String protocolo;
    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDoRegistro;

}
