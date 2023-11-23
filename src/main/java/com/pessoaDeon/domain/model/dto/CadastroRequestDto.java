package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.endereco.TipoMoradia;
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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroRequestDto {
	

	@NotBlank
	@NotNull
	@Size(max=255)
	private String nome;
	private String cpf;
	@NotNull
	@NotBlank
	@Size(max=255)
	private String nomeMae;

	@Size(max=255)
	private String nomePai;

	@Size(max=50)
	private String alcunha;

	@Size(max=255)
	private String nomeSocial;

	@NotNull
	private Sexo sexo;

	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

	private EstadoCivil estadoCivil;

	@NotNull
	private OrientacaoSexual orientacaoSexual;

	@NotNull
	private Deficiencia deficiencia;

	@NotNull
	private Raca corPele;
	private Profissao profissao;
	private Escolaridade escolaridade;

	@NotNull
	private Pais pais;
	@NotNull
	private Estado estadoNaturalidade;
	@NotNull
	private Cidade cidadeNaturalidade;
	@NotNull
	private IdentidadeGenero identidadeGenero;
	private Boolean estrangeiro;

	@NotNull
	private TipoDocumento tipoDocumento;
	@NotNull
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
	private TipoMoradia tipoMoradia;

	private String complemento;
	private String referencia;

// 	contato
	private String telefone;
	private Boolean telefoneAtual;
	private String tipoCelular;
	private Boolean tipoWhatsapp;
	private Boolean tipoTelegram;

//email
	@NotBlank
	@Email
	private String email;
	@NotNull
	private Boolean emailAtual = true;
	private String senha;
	
}
