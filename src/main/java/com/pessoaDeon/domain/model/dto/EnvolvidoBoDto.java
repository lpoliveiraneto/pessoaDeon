package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.model.listas.Deficiencia;
import com.pessoaDeon.domain.model.listas.Escolaridade;
import com.pessoaDeon.domain.model.listas.Estado;
import com.pessoaDeon.domain.model.listas.EstadoCivil;
import com.pessoaDeon.domain.model.listas.IdentidadeGenero;
import com.pessoaDeon.domain.model.listas.OrientacaoSexual;
import com.pessoaDeon.domain.model.listas.Pais;
import com.pessoaDeon.domain.model.listas.Profissao;
import com.pessoaDeon.domain.model.listas.Raca;
import com.pessoaDeon.domain.model.listas.Sexo;
import com.pessoaDeon.domain.model.listas.TipoDocumento;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnvolvidoBoDto {
	
	private Integer idEnvolvido;

	private String nome;
	
	private String nomeMae;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

	private String tipoParticipacao;

}
