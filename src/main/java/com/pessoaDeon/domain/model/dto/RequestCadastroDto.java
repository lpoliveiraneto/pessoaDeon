package com.pessoaDeon.domain.model.dto;

import lombok.Data;

@Data
public class RequestCadastroDto {
	
	private CadastroRequestDto cadastro;
	
	private byte[] files;

}
