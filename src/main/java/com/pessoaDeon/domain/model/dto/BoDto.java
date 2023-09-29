package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.listas.Bairro;
import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.model.listas.Estado;
import com.pessoaDeon.domain.model.listas.TipoLocal;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoDto {	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd")
	private LocalDate dataFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime horaFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
	
	private String relato;

	private String relatoEditado;
	
	private String cep;
	
	private String complemento;
	
	private String numeroLocal;
	
	private String logradouro;
	
	private Bairro bairro;

	private Cidade cidade;

	private Estado estado;
	
    private TipoLocal tipoLocal;

	private Integer fkNaturezaDeon;
	
}
