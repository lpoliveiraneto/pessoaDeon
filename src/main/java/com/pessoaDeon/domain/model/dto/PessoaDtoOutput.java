package com.pessoaDeon.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.enumeration.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class PessoaDtoOutput {

    private Long id;
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

}
