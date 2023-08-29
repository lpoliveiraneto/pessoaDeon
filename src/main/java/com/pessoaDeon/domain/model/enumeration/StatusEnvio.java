package com.pessoaDeon.domain.model.enumeration;

import lombok.Getter;

@Getter
public enum StatusEnvio {

	OK("Enviado"),
	PE("Pendente"),
	ER("Erro");
	
	private String descricao;

	private StatusEnvio(String descricao) {
		this.descricao = descricao;
	}
	
	
}
