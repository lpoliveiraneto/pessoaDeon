package com.pessoaDeon.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity(name = "identidadeGenero")
@Getter
public class IdentidadeGenero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idIdentidadeGenero;
	
	@NotNull
	@Size(max=255)
	private String nome;
	
	@NotNull
	@Size(max=255)
	private String descricao;

	@NotNull
	@Size(max=255)
	private String valor;
	
}
