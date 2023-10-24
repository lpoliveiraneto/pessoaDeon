package com.pessoaDeon.domain.model.util;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ConfiguracaoUpload implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4519096327971944423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String path;
	
	private String descricao;
	
	private Boolean status;
	
}
