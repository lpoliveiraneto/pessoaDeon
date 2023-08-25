package com.pessoaDeon.domain.model.util;

public enum EnvioStatus {
	
	OK("Enviado"),
	ER("Erro"),
	PE("Pendente");
	
	private String descricao;
	
	private EnvioStatus(String descricao) {
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
