package com.pessoaDeon.domain.model.dto.analista;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalistaReturnDto {
	
	private AnalistaResponseDto analistaResponseDto;
	
	private String message;
}
