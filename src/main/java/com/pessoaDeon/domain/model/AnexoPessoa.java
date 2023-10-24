package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.util.ConfiguracaoUpload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class AnexoPessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8339414419620155235L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_upload")
	private LocalDateTime dataUpload;
	
	@ManyToOne
	@JoinColumn(name = "fk_pessoa")
	private Pessoa pessoa;
	
	private String caminho;
	
	private String nomeArquivo;
	
	private String tipoArquivo;
	
	@ManyToOne
	@JoinColumn(name = "fk_config_upload")
	private ConfiguracaoUpload configUpload;
	
}
