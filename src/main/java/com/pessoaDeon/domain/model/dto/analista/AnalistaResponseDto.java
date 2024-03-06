package com.pessoaDeon.domain.model.dto.analista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.analista.Cargo;
import com.pessoaDeon.domain.model.analista.Funcao;
import com.pessoaDeon.domain.model.security.Perfil;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

public class AnalistaResponseDto {

	private Integer idAnalista;
	
	private Integer idFuncionario;
	
	private Boolean ativo;
	
	private String nome;
	
	private String nomeMae;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	private String login;
	
	private String matricula;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataInsercao;
	
	private List<Perfil> perfis = new ArrayList<>();
	
    private Cargo cargo;    

    private Funcao funcao;
	
	public Integer getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(Integer idAnalista) {
		this.idAnalista = idAnalista;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDateTime getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(LocalDateTime dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
}
