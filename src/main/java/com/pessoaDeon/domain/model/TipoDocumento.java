package com.pessoaDeon.domain.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tipo_documento", schema = "listas")
public class TipoDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoDocumento;
	
	@NotNull
	@Size(max=255)
	private String descricao;
	
	@NotNull
	@Size(max=255)
	private String valor;
	
}
