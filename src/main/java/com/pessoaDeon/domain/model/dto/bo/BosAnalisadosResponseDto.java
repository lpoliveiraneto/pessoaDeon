package com.pessoaDeon.domain.model.dto.bo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BosAnalisadosResponseDto {

    private Integer idBo;

    private String protocolo;

    private String nomeAnalista;

    private String natureza;

    private LocalDateTime dataAnalise;

    private LocalDateTime dataRegistro;

}
