package com.pessoaDeon.domain.model.listas;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="unidade_destino", schema="listas")
public class UnidadeDestino implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7490997098608140245L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUnidade;
	
	private String descricao;
	
	@Column(name = "fk_unidade_sigma")
	private Integer fkUnidadeSigma;
	
	private String sigla;

	
}
