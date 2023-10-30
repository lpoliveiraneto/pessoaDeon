package com.pessoaDeon.domain.model.dto.analista;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalistaSigmaDeonDto {
	
	private AnalistaRequest analistaRequest;

	private AnalistaResponseDto analistaResponseDto;
}
