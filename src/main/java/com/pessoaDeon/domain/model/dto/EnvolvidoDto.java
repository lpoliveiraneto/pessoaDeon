package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import com.pessoaDeon.domain.model.listas.Bairro;
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
import com.pessoaDeon.domain.model.listas.TipoLocal;
import com.pessoaDeon.domain.model.pessoa.Pessoa;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnvolvidoDto {

	@Size(max=255)
	private String nome;
	
	@Size(max=255)
	private String nomeMae;

	@Size(max=255)
	private String nomePai;

	@Size(max=50)
	private String alcunha;

	@Size(max=255)
	private String nomeSocial;

	private Sexo sexo;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

	private EstadoCivil estadoCivil;

	private OrientacaoSexual orientacaoSexual;

	private Deficiencia deficiencia;

	private Raca corPele;
	private Profissao profissao;
	private Escolaridade escolaridade;

	private Pais pais;
	private Estado estadoNaturalidade;
	private Cidade cidadeNaturalidade;
	private IdentidadeGenero identidadeGenero;
	private Boolean estrangeiro;

	private TipoDocumento tipoDocumento;
	private String numeroDocumento;

	private String telefone;
	private String email;
	
	private Integer fk_pessoa;
	
	private TipoParticipacao tipoParticipacao;

	private EnderecoEnvolvidoDto enderecoEnvolvido;

}
