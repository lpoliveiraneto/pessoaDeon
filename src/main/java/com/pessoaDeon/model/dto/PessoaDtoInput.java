package com.pessoaDeon.model.dto;

import com.pessoaDeon.model.enumeration.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaDtoInput {

    private String nome;
    private String nomeMae;
    private String nomePai;
    private String alcunha;
    private String cpf;
    private LocalDateTime dataNascimento;
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
