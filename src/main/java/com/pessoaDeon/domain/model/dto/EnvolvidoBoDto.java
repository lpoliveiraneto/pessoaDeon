package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EnvolvidoBoDto {
	
	private Integer idEnvolvido;

	private String nome;
	
	private String nomeMae;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

	private String tipoParticipacao;

}
