package com.pessoaDeon.domain.model.enumeration;

public enum PerfilUsuario {

    ROLE_USER("usuario"),
    ROLE_ADMIN("administrador");

    private String descricao;

    private PerfilUsuario(String descricao){
        this.descricao = descricao;
    }
}
