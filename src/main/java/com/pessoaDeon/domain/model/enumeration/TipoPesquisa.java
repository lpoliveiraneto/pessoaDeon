package com.pessoaDeon.domain.model.enumeration;

import lombok.Getter;

@Getter
public enum TipoPesquisa {

    PROTOCOLO("Protocolo"),
    COMUNICANTE("Comunicante"),
    CPF("CPF"),
    NATUREZA("Natureza"),
    DATA_REGISTRO("Data registro");

    private String descricao;

    private TipoPesquisa(String descricao){
        this.descricao = descricao;
    }

}
