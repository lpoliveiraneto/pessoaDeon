package com.pessoaDeon.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaDto {

    private Long id;

    private String nome;

    private String nomeMae;

    private String nomePai;

    private String alcunha;

    private String cpf;

    private LocalDateTime dataNascimento;
}
