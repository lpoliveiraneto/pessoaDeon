package com.pessoaDeon.domain.model.dto.integracao;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class EnvolvidoRequestDto {

//	dados da pessoa
	private String nome;
	private String nomeMae;
	private String nomePai;
	private String apelido;
	private String cpf;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	private String sexo;
	private Boolean desconhecido;
	private String 	nomeSocial;
	private String	orientacaoSexual;
	private String identidadeGenero;
	private String deficiencia;
	private String racaCor;
	private Integer pais;
	private Integer estado;
	private Integer cidade;
	private Integer profissao;
	private String	escolaridade;
	private String	estadoCivil;
	private Boolean usarNomeSocial;

	private EnderecoRequestDto endereco;
	private ContatoRequestDto contato;
	
//	tipo participacao natureza; comunicante, vitima, infrator.
	private String tipoParticipacao;

}
