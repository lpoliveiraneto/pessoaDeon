package com.pessoaDeon.domain.model.dto.pecas;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RespostasFormularioDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idPergunta;
	private List<RespostaPorPerguntaDto> respostas;
}
