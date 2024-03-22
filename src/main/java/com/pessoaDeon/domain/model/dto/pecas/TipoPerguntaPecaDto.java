package com.pessoaDeon.domain.model.dto.pecas;
import java.util.List;

import com.pessoaDeon.domain.model.pecas.formulario_risco.TipoRespostaFormularioRisco;

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
	
	private Boolean checkbox;

	private List<TipoRespostaFormularioRisco> tipoRespostaFormularioRisco;
	
	public TipoPerguntaPecaDto(Integer id, String pergunta, Boolean ativo, Boolean checkbox) {
		this.id = id;
		this.pergunta = pergunta;
		this.ativo = ativo;
		this.checkbox = checkbox;
	}
}
