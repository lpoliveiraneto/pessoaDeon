package com.pessoaDeon.domain.model.dto;

import com.pessoaDeon.domain.model.endereco.Logradouro;
import com.pessoaDeon.domain.model.pessoa.Pessoa;

import lombok.Getter;

@Getter
public class EnderecoDtoInput {

    private String numero;
    private String referencia;
    private Boolean atual;
    private Pessoa pessoa;
    private Logradouro logradouro;
}
