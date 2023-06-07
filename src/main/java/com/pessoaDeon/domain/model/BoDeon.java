package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataFato;
	
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date horaFato;
	
	private String logradouro;
	private String complemento;
	private String pontoReferencia;
	
	@Column(columnDefinition = "text")
	private String relato;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_estado")
	private Estado fkEstado;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_cidade")
	private Cidade fkCidade;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_bairro")
	private Bairro fkBairro;
	
	@NotNull
	private String numeroLocal;
	
//	@NotNull
	private String cep;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_tipo_local")
	private TipoLocal tipoLocal;
}
