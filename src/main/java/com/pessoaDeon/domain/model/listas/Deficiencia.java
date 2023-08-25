package com.pessoaDeon.domain.model.listas;

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

@Entity(name = "deficiencia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="deficiencia", schema="listas")
public class Deficiencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDeficiencia;
	
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
