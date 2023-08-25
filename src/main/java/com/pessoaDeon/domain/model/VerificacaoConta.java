package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.pessoaDeon.domain.model.security.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VerificacaoConta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;
	
	private String codigo;
	
	@Column(name = "data_solicitacao")
	private LocalDateTime dataSolicitacao;
	
	@Column(name = "expiracao_codigo")
	private LocalDateTime expiracaoCodigo;

	public VerificacaoConta(Usuario usuario, String codigo) {
		this.usuario = usuario;
		this.codigo = codigo;
	}

}
