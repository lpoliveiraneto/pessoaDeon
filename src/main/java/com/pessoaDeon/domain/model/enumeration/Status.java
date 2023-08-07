package com.pessoaDeon.domain.model.enumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Status {

    VA("VALIDO"),
    IV("INVALIDO"),
    PE("PENDENTE");

    private String descricao;

    private Status(String descricao){
        this.descricao = descricao;
    }

}
