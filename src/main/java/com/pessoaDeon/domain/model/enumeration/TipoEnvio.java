package com.pessoaDeon.domain.model.enumeration;

import lombok.Getter;

@Getter
public enum TipoEnvio {

	CD("Delegacia Online - Confirme seu email"),
	RS("Delegacia Online - Redefinição de senha");
	
	private String descricao;

	private TipoEnvio(String descricao) {
		this.descricao = descricao;
	}
	
	
}
