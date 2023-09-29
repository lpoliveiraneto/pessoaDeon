package com.pessoaDeon.domain.model.dto.integracao;

import lombok.Data;

@Data
public class ContatoRequestDto {

	private String numero;
	
	private Boolean whatsapp = false;
	
	private String email;
	
}
