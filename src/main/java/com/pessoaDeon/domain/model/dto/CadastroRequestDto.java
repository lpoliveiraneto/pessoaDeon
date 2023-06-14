package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.Bairro;
import com.pessoaDeon.domain.model.Cidade;
import com.pessoaDeon.domain.model.Deficiencia;
import com.pessoaDeon.domain.model.Escolaridade;
import com.pessoaDeon.domain.model.Estado;
import com.pessoaDeon.domain.model.EstadoCivil;
import com.pessoaDeon.domain.model.IdentidadeGenero;
import com.pessoaDeon.domain.model.OrientacaoSexual;
import com.pessoaDeon.domain.model.Pais;
import com.pessoaDeon.domain.model.Profissao;
import com.pessoaDeon.domain.model.Raca;
import com.pessoaDeon.domain.model.Sexo;

import com.pessoaDeon.domain.model.TipoLocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroRequestDto {
	
//	pessoa
	private String nome;
	private String cpf;
	private String nomeMae;
	private String nomePai;
	private String alcunha;
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
//	private Nacionalidade nacionalidade;
	private Pais pais;
	private Estado estadoNaturalidade;
	private Cidade cidadeNaturalidade;
	private IdentidadeGenero identidadeGenero;
	private Boolean estrangeiro;
	private String tipoDocumento;
	private String numeroDocumento;

//	logradouro	
	private String cep;
	private Estado estado;
	private Cidade cidade;
	private String logradouro;
	private Bairro bairro;
	
//	endereco
	private String numero;
	private Boolean enderecoAtual;
	private TipoLocal tipoLocal;

	private String complemento;
	private String referencia;

// 	contato
	private String telefone;
	private Boolean telefoneAtual;
	private String tipoCelular;
	private Boolean tipoWhatsapp;
	private Boolean tipoTelegram;

//email	
	private String email;
	private Boolean emailAtual;
	
}
