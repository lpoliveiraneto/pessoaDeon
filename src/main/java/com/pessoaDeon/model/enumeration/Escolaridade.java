package com.pessoaDeon.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Escolaridade {

    SI("Sem instrução"),
    EFI("Ensino Fundamental Incompleto"),
    EFC("Ensino Fundamental Completo"),
    EMI("Ensino Médio Incompleto"),
    EMC("Ensino Médio Completo"),
    ESI("Ensino Superior Incompleto"),
    ESC("Ensino Superior Completo"),
    PG("Pós-Graduação");

    private String descricao;

}
