package com.pessoaDeon.domain.model.enumeration;

public enum EstadoCivil {

	SO("Solteiro"),
	CA("Casado(a)"),
	SP("Separado(a)"),
	CV("Convivente"),
	DV("Divorciado(a)"),
	UE("União Estável"),
	VI("Viuvo(a)");
	
	private String descricao;
	
	private EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
