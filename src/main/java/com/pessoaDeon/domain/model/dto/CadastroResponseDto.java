package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.usuario.RespostaAnaliseUsuario;

import lombok.Data;

@Data
public class CadastroResponseDto {

	private String nome;
	private LocalDate dataNascimento;
	private String nomeMae;
	private String nomePai;
	private String alcunha;
	private String nomeSocial;
	private String sexo;
	private String estadoCivil;
	private String orientacaoSexual;
	private String deficiencia;
	private String corPele;
	private String profissao;
	private String escolaridade;

	private String pais;
	private String estadoNaturalidade;
	private String cidadeNaturalidade;
	private String identidadeGenero;
	private Boolean estrangeiro;

	private String tipoDocumento;
	private String numeroDocumento;

	//	logradouro	
	private String cep;
	private String estado;
	private String cidade;
	private String logradouro;
	private String bairro;
	
	//	endereco
	private String numero;
	private Boolean enderecoAtual;
	private String tipoMoradia;

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
	
//	lista de anexos da pessoa;
	private List<String> listaAnexos;
	
//	retorna a foto de perfil da pessoa em base64
	private String fotoPerfil;
	
	private RespostaAnaliseUsuario respostaUsuario;
	
	private Status statusUsuario;
	
	private String descricaoStatusUsuario;
}
