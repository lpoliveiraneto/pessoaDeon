package com.pessoaDeon.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.enumeration.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaDtoInput {

    private String nome;
    private String nomeMae;
    private String nomePai;
    private String alcunha;
    private String cpf;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String nomeSocial;
    private OrientacaoSexual orientacaoSexual;
    private IdentidadeGenero identidadeGenero;
    private Deficiencia deficiencia;
    private CorPele corPele;
    private int pais;
    private int estado;
    private int cidade;
}
