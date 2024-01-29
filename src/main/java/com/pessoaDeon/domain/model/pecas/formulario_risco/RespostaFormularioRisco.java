package com.pessoaDeon.domain.model.pecas.formulario_risco;

import com.pessoaDeon.domain.model.pecas.Peca;
import com.pessoaDeon.domain.model.pecas.formulario_risco.PerguntaRespostaFormularioRisco;
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
@Table(name="respostas_formulario_peca", schema="formulario_risco")
public class RespostaFormularioRisco {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="fk_pergunta_resposta")
	private PerguntaRespostaFormularioRisco perguntaRespostaFormularioRisco;
	
	@ManyToOne
	@JoinColumn(name="fk_peca")
	private Peca peca;
}
