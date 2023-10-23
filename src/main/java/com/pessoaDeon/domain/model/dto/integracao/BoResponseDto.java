package com.pessoaDeon.domain.model.dto.integracao;

import java.util.Date;

import lombok.Data;

@Data
public class BoResponseDto {

	private Integer idBoDeon;
	
	private Integer idBoSigma;
	
	private Integer numeroBo;
	
	private Date ano;
	
	private Date dataRegistroSigma;
	
}
