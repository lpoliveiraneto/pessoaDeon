package com.pessoaDeon.domain.model.pecas;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requerimento_peca_mpu", schema = "peca")
public class RequerimentoPecaMpu {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String observacao;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDate dataCriacao;

	@ManyToOne
	@JoinColumn(name="fk_requerimento")
	private RequerimentoMpu requerimentoMpu;
	
	@ManyToOne
	@JoinColumn(name="fk_peca")
	private Peca peca;
	
	private Boolean status_ativo;

	private String ip;
}
