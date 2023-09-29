package com.pessoaDeon.domain.model.dto.integracao;

import lombok.Data;

@Data
public class EnderecoRequestDto {

	private String paisEndereco;
	private String estadoEndereco;
	private String ufEndereco;
	private String cidadeEndereco;
	private String bairro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String referencia;
	private String cep;

	private Integer idEstadoEndereco;
	private Integer idCidadeEndereco;
	private Integer idBairro;
	
}
