package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.Cidade;
import com.pessoaDeon.domain.model.Estado;
import com.pessoaDeon.domain.model.Pais;
import com.pessoaDeon.domain.model.Profissao;
import com.pessoaDeon.domain.model.enumeration.CorPele;
import com.pessoaDeon.domain.model.enumeration.Deficiencia;
import com.pessoaDeon.domain.model.enumeration.Escolaridade;
import com.pessoaDeon.domain.model.enumeration.EstadoCivil;
import com.pessoaDeon.domain.model.enumeration.IdentidadeGenero;
import com.pessoaDeon.domain.model.enumeration.Nacionalidade;
import com.pessoaDeon.domain.model.enumeration.OrientacaoSexual;
import com.pessoaDeon.domain.model.enumeration.Sexo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CadastroRequestDto {
	
//	pessoa
	private String nome;
	private String cpf;
	private String nomeMae;
	private String nomePai;
	private String alcunha;
	private String nomeSocial;
	private Sexo sexo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private EstadoCivil estadoCivil;
	private OrientacaoSexual orientacaoSexual;
	private Deficiencia deficiencia;
	private CorPele corPele;
	private IdentidadeGenero identidadeGenero;
	private Profissao profissao;
	private Escolaridade escolaridade;
	private Nacionalidade nacionalidade;
	private Pais pais;
	private Estado estadoNaturalidade;
	private Cidade cidadeNaturalidade;
	
	
//	logradouro	
	private String cep;
	private Estado estado;
	private Cidade cidade;
	private String logradouro;
	
//	endereco
	private String numero;
	private Boolean enderecoAtual;
	private String tipoLocal;
	private String complemento;
	private String referencia;

// 	contato
	private String telefone;
	private Boolean telefoneAtual;
	private String tipoCelular;

//email	
	private String email;
	private Boolean emailAtual;
	
}
