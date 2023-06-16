package com.pessoaDeon.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "orientacaoSexual")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orientacao_sexual", schema="listas")
public class OrientacaoSexual {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrientacaoSexual;
	
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
