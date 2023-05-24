package com.pessoaDeon.domain.model.dto;

import lombok.Data;

@Data
public class CidadeDto {

	private Integer Id;
	private String Nome;
	private String Latitude;
	private String Longitude;
	private String UF;
	private String CEP;
	private String CodigoIBGE;
	
}
