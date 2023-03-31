package com.pessoaDeon.model.enumeration;

public enum OrientacaoSexual {

    HE("Heterossexual", "Quando o interesse é por uma pessoa do sexo oposto."),
    HO("Homossexual", "Quando o interesse é por uma pessoa do mesmo sexo."),
    BI("Bissexual", "Pessoas que sentem atração por homens e mulheres."),
    PA("Pansexual", "Atração por outras pessoas, independentemente da identidade de gênero."),
    AS("Assexual", "Não sente nenhum tipo de desejo/atração sexual ou afetiva."),
    OU("Outra", ""),
    NI("Não Informado", "");

    private String tooltip;
    private String descricao;

    private OrientacaoSexual(String descricao, String tooltip){
        this.tooltip = tooltip;
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getTooltip(){
        return tooltip;
    }

    public void setTooltip(){
        this.tooltip = tooltip;
    }
}
