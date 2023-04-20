package com.pessoaDeon.domain.model.dto;

import com.pessoaDeon.domain.model.enumeration.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PessoaDtoOutput {

    private Long id;
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

}
