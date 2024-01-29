package com.pessoaDeon.domain.model.pecas;

import java.io.Serializable;
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
@Table(name="tipo_pergunta_peca", schema="formulario_risco")
public class TipoPerguntaPeca implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String pergunta;
	
	@ManyToOne
	@JoinColumn(name="fk_bloco")
	private BlocoPerguntas bloco;
	
	private Boolean ativo;
}
