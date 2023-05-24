package com.pessoaDeon.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BairroDto {

	private Integer Id;
	
	private String Nome;
	
	private CidadeDto Cidade;
	
}
