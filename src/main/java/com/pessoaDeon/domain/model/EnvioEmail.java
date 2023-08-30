package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.pessoaDeon.domain.model.enumeration.StatusEnvio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EnvioEmail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_registro")
	private LocalDateTime dataRegistro;

	private String destinatario;
	
	@Enumerated(EnumType.STRING)
	private StatusEnvio status = StatusEnvio.PE;
	
}
