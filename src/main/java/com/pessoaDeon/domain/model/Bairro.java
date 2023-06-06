package com.pessoaDeon.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bairro implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBairro;
	
	@JsonIgnore
	private Integer idBairroApi;
	
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@JsonIgnore
	@JoinColumn(name = "fk_cidade")
	private Cidade cidade;

}
