package com.pessoaDeon.domain.model.enumeration;

public enum Nacionalidade {
	
	BR("Brasileiro"),
	ET("Estrangeiro");

	private String descricao;

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	private Nacionalidade(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
