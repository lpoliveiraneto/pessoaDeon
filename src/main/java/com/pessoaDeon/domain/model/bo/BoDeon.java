package com.pessoaDeon.domain.model.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.natureza.NaturezaBo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class BoDeon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4270639592291268512L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBo;
	
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd")
	private LocalDate dataFato;
	
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "HH:mm")
	private LocalTime horaFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;

	@Column(columnDefinition = "text")
	private String relato;

	@Column(columnDefinition = "text")
	private String relatoEditado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<NaturezaBo> listaNaturezas;
	
	private String numeroBo;
	
	private Status status;
}
