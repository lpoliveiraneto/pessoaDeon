package com.pessoaDeon.domain.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.Bairro;
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

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date horaFato;
	
	private Estado fkEstado;
	private Cidade fkCidade;
	private Bairro fkBairro;
	private String numeroLocal;
	private String cep;
	private TipoLocal tipoLocal;
//	private Integer tipoLogradouro;	
	private String logradouro;
	private String complemento;
	private String pontoReferencia;	
	private String relato;
	
}
