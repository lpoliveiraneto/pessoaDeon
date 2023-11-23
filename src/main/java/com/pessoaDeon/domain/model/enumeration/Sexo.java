package com.pessoaDeon.domain.model.enumeration;

public enum Sexo {
    M("Masculino"),
    F("Feminino"),
    NI("Não informado");

    private String descricao;

    private Sexo(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
