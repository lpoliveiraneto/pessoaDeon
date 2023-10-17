package com.pessoaDeon.domain.model.dto;

import com.pessoaDeon.domain.model.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BosAnalisadosResponseDto {

    private Integer idBo;

    private String protocolo;

    private String nomeAnalista;

    private String natureza;

    private LocalDate dataAnalise;

    private LocalDate dataRegistro;

    private Status status;
}
