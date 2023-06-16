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

@Entity(name = "estadoCivil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="estado_civil", schema="listas")
public class EstadoCivil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstadoCivil;
	
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
