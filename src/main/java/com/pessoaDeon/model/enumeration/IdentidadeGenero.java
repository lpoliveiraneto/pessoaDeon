package com.pessoaDeon.model.enumeration;

import lombok.Getter;

@Getter
public enum IdentidadeGenero {
    H("Homem", ""),
    M("Mulher", ""),
    T("Travesti", "Indivíduo do sexo masculino que usa roupas e adota expressões femininas mas que não necessariamente deseja mudar suas características primárias."),
    CI("Cisgênero", "É a pessoa que se identifica com o sexo biológico designado no momento de seu nascimento."),
    TR("Transgênero", "É quem se identifica com um gênero diferente daquele atribuído no nascimento."),
    NB("Não-Binário", "Aquele que não se identifica completamente com o 'gênero de nascença', nem com outro gênero."),
    HT("Homem transsexual", "É uma pessoa que nasceu biologicamente mulher, mas que se identifica e se sente um homem."),
    MT("Mulher transsexual", "É uma pessoa que nasceu biologicamente homem, mas que se identifica e se sente uma mulher."),
    NI("Não Informado", ""),
    OU("Outra", "");

    private String descricao;
    private String tooltip;

    private IdentidadeGenero(String descricao, String tooltip){
        this.tooltip = tooltip;
        this.descricao = descricao;
    }

}
