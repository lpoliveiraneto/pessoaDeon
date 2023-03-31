package com.pessoaDeon.model.enumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CorPele {

    BR("Branca"),
    PR("Negra"),
    AM("Amarela"),
    PA("Parda"),
    ID("Indigena"),
    O("Outra");

    private String descricao;

    private CorPele(String descricao){
        this.descricao = descricao;
    }

}
