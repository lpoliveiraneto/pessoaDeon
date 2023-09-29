package com.pessoaDeon.domain.model.dto.integracao;

import lombok.Data;

@Data
public class EquipeRequestDto {

	private Integer fkFuncionarioSigma;
	
	private Boolean registrante = false;
	
	private Boolean autoridade = false;
	
}
