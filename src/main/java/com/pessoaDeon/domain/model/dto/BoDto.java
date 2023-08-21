package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.Bairro;
import com.pessoaDeon.domain.model.BoDeon;
import com.pessoaDeon.domain.model.Cidade;
import com.pessoaDeon.domain.model.Estado;
import com.pessoaDeon.domain.model.TipoLocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoDto {	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd")
	private LocalDate dataFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime horaFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataRegistro;
	
	private String protocolo;

	private String relato;

	private String relatoEditado;
	
	private String cep;
	
	private String complemento;
	
	private Integer numeroLocal;
	
	private String logradouro;
	
	private Bairro bairro;

	private Cidade cidade;

	private Estado estado;
	
    private TipoLocal tipoLocal;
	
	private BoDeon bo;
	
}
