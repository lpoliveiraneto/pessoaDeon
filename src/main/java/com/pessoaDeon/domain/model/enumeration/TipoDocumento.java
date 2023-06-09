package com.pessoaDeon.domain.model.enumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TipoDocumento {

    CPF("CPF"),
    RNE("RNE"),
    PA("Passaporte");

    private String descricao;

    private TipoDocumento(String descricao){
        this.descricao = descricao;
    }

}
