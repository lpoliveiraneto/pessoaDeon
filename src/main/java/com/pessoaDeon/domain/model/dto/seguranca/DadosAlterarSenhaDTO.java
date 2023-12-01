package com.pessoaDeon.domain.model.dto.seguranca;

import lombok.Getter;

@Getter
public class DadosAlterarSenhaDTO {
    private String senhaNova;
    private String senhaAntiga;
    private Integer idUsuario;
}
