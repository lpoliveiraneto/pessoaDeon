package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class FotoPerfil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8645593556973457512L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_upload")
	private LocalDateTime dataUpload;
	
//	@ManyToOne
	@Column(name = "fk_pessoa")
	private Integer pessoa;
	
	private String caminho;
	
	private String nomeArquivo;
	
	private String tipoArquivo;
	
	@ManyToOne
	@JoinColumn(name = "fk_config_upload")
	private ConfiguracaoUpload configUpload;
	
}
