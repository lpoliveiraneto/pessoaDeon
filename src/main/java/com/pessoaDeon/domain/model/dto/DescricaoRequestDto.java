package com.pessoaDeon.domain.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class DescricaoRequestDto {

	@NotEmpty
	private String nome;
}
