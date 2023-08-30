package com.pessoaDeon.domain.model.dto;

import lombok.Data;

@Data
public class NaturezaDto {

	private Integer idNatureza;
	
	private String nome;
	
	private String codigo;
	
	private String descricao;
	
	private String tipoNatureza;

	private Integer naturezaSigma;
	
}
