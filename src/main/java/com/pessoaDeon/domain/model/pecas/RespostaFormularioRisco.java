package com.pessoaDeon.domain.model.pecas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="respostas_formulario_peca", schema="peca")
public class RespostaFormularioRisco {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="fk_pergunta_resposta")
	private PerguntaResposta perguntaResposta;
	
	@ManyToOne
	@JoinColumn(name="fk_peca")
	private Peca peca;
}
