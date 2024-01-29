package com.pessoaDeon.domain.model.dto.pecas;
import java.util.List;

import com.pessoaDeon.domain.model.pecas.TipoRespostaPeca;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TipoPerguntaPecaDto {
    
    private Integer id;
	
	private String pergunta;
	
	private Boolean ativo;

	private List<TipoRespostaPeca> tipoRespostaPeca;
	
	public TipoPerguntaPecaDto(Integer id, String pergunta, Boolean ativo) {
		this.id = id;
		this.pergunta = pergunta;
		this.ativo = ativo;
	}
}
