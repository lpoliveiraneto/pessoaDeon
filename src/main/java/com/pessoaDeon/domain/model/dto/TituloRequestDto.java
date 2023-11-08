package com.pessoaDeon.domain.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class TituloRequestDto {

	@NotEmpty
	private String nome;
	
	private List<DescricaoRequestDto> listaDescricaoAviso;
}
