package com.pessoaDeon.domain.model.enumeration;

import lombok.Getter;

@Getter
public enum Status {

    VA("VALIDO"),
    IV("INVALIDO"),
    EA("EM ANALISE"),
    PE("PENDENTE");

    private String descricao;

    private Status(String descricao){
        this.descricao = descricao;
    }

}
