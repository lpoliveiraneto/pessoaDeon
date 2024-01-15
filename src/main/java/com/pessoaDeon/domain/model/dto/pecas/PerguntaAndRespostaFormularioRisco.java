package com.pessoaDeon.domain.model.dto.pecas;

import com.pessoaDeon.domain.model.pecas.PerguntaResposta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaAndRespostaFormularioRisco {
    
    private Integer id;
	private String observacao;
	private PerguntaResposta perguntaresposta;
}
