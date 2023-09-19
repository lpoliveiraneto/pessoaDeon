package com.pessoaDeon.domain.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BosPessoaResponseDto {

	private Integer idBo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataRegistro;
	
	private String protocolo;
	
	private String nomeNatureza;
	
	private String codigoNatureza;
	
	private String statusAnalise;
	
}
