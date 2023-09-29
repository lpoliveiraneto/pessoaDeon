package com.pessoaDeon.domain.model.dto.integracao;




public enum TipoParticipacaoNatureza {
		
//	CD("Condutor"),
//	TE("Testemunha"),
	CM("Comunicante"),
	VT("Vítima"),
//	VF("Vítima Fatal"),
	II("Investigado / Infrator");
//	NE("Não Envolvido");
	
	private String descricao;

	private TipoParticipacaoNatureza(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	

}
