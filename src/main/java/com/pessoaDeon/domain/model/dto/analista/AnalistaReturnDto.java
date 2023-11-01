package com.pessoaDeon.domain.model.dto.analista;

import com.pessoaDeon.domain.model.pessoa.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalistaReturnDto {
	
	private AnalistaResponseDto analistaResponseDto;
	
	private String message;
	
	private Pessoa pessoa;
}
