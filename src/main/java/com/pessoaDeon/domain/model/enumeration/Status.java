package com.pessoaDeon.domain.model.enumeration;

import lombok.Getter;

@Getter
public enum Status {

    VA("APROVADO"),
    IV("RECUSADO"),
    EA("EM ANÁLISE"),
    PE("PENDENTE");

    private String descricao;

    private Status(String descricao){
        this.descricao = descricao;
    }

}
