package com.pessoaDeon.domain.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.util.EnumToObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BosPessoaResponseDto {

	private Integer idBo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataRegistro;
	
	private String protocolo;
	
	private String nomeNatureza;
	
	private String codigoNatureza;
	
	private EnumToObject status;
	
	private String respostaAnalise;
	
}
