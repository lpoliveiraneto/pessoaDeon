package com.pessoaDeon.domain.model.dto;

import lombok.Getter;

@Getter
public class EnderecoDtoInput {

    private int numero;
    private String referencia;
    private boolean atual;
    private int pessoa;
    private int logradouro;
}
