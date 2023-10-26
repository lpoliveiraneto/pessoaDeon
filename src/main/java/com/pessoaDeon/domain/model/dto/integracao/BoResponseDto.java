package com.pessoaDeon.domain.model.dto.integracao;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class BoResponseDto {

	private Integer idBoDeon;
	
	private Integer idBoSigma;
	
	private Integer numeroBo;
	
	private LocalDate ano;
	
	private Date dataRegistroSigma;
	
}
