package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.model.listas.Estado;
import com.pessoaDeon.domain.model.listas.Pais;
import com.pessoaDeon.domain.model.enumeration.CorPele;
import com.pessoaDeon.domain.model.enumeration.Deficiencia;
import com.pessoaDeon.domain.model.enumeration.IdentidadeGenero;
import com.pessoaDeon.domain.model.enumeration.OrientacaoSexual;
import com.pessoaDeon.domain.model.enumeration.Sexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDtoInput {

    private String nome;
    private String nomeMae;
    private String nomePai;
    private String alcunha;
    private String cpf;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String nomeSocial;
    private OrientacaoSexual orientacaoSexual;
    private IdentidadeGenero identidadeGenero;
    private Deficiencia deficiencia;
    private CorPele corPele;
    private Pais pais;
    private Estado estadoNaturalidade;
    private Cidade cidadeNaturalidade;
}
