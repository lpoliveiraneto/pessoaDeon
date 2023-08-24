package com.pessoaDeon.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnderecoLocalFato implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEnderecoLocal;

	@NotNull
	private String cep;
	
	private String complemento;
	
	private Integer numeroLocal;
	
	private String referencia;
	
	private String logradouro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "fk_bairro")
	private Bairro bairro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "fk_cidade")
	private Cidade cidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "fk_estado")
	private Estado estado;
	
	@ManyToOne
    @JoinColumn(name = "fk_tipo_local")
    private TipoLocal tipoLocal;
	
	@ManyToOne
	@JoinColumn(name = "fk_bo")
	private BoDeon bo;
	
}
