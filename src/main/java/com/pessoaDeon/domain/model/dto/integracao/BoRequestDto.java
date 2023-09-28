package com.pessoaDeon.domain.model.dto.integracao;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.listas.TipoLocal;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BoRequestDto {
	
	private Integer idBoDeon;
	
	private Integer unidade;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate dataFato;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistroRascunho;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFato;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ano;

	private String bairro;

	private String cidade;

	private String uf;

	private String pais;
	
	private String	latLong;
	
	private String	cep;
	
	private Integer	fkPais;
	
	private Integer	fkEstado;
	
	private Integer	fkCidade;
	
	private Integer	fkBairro;
	
	private String	logradouro;
	
	private String	numeroLocal;
	
	private String	referencia;
	
	private TipoLocal tipoLocal;
	
	private String	complemento;
	
	@NotEmpty(message = "preencha o relato!")
	private String	relato;

}
