package com.pessoaDeon.domain.model.pecas.formulario_risco;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pergunta_resposta", schema="formulario_risco")
public class PerguntaRespostaFormularioRisco implements Serializable {
    
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="fk_pergunta")
	private TipoPerguntaFormularioRisco pergunta;
	
	@ManyToOne
	@JoinColumn(name="fk_resposta")
	private TipoRespostaFormularioRisco resposta;
	
	private Boolean ativa;
}
