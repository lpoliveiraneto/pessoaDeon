package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoDeon implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBo;
	
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd")
	private LocalDate dataFato;
	
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "HH:mm:ss")
	private LocalTime horaFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataRegistro;
	
	@NotEmpty
	private String protocolo;

	@Column(columnDefinition = "text")
	private String relato;

	@Column(columnDefinition = "text")
	private String relatoEditado;
}
