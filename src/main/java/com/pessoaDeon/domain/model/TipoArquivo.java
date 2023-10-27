package com.pessoaDeon.domain.model;

import lombok.Getter;

@Getter
public enum TipoArquivo {
	
	AN("Anexo Documentos"),
	FP("Foto Perfil");
	
	private String descricao;

	private TipoArquivo(String descricao) {
		this.descricao = descricao;
	}

}
