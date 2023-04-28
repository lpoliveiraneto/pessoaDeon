package com.pessoaDeon.domain.model.enumeration;

public enum EstadoCivil {

    ST("Solteiro(a)"),
    //	@SerializedName("Casado(a)")
    CS("Casado(a)"),
    //	@SerializedName("Separado(a) Judicialmente")
    SP("Separado(a)"),

    CV("Convivente"),

    DV("Divorciado"),
    //	@SerializedName("União Estável")
    UE("União Estável"),
    //	@SerializedName("Viúvo(a)")
    VI("Viúvo(a)");

    private String descricao;

    private EstadoCivil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
