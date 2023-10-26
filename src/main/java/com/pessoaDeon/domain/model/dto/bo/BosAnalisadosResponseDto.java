package com.pessoaDeon.domain.model.dto.bo;

import com.pessoaDeon.domain.model.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
