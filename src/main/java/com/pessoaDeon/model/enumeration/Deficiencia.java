package com.pessoaDeon.model.enumeration;

import lombok.Getter;

@Getter
public enum Deficiencia {

    NONE("Nenhuma Deficiência", ""),

    DF("Deficiência Física",
            "Caracterizada pela perda de funcionalidade dos membros superiores ou inferiores,"
                    + " por amputação, atrofia, paralisia ou deformidade, "
                    + "podendo ou não ocasionar o uso de próteses ou órteses, "
                    + "bem como a redução de mobilidade."),

    DI("Deficiência Intelectual",
            "Caracteriza-se por alterações cognitivas, "
                    + "estando relacionada com a capacidade para o aprendizado e a compreensão em geral, "
                    + "manifestando-se, em regra, desde a infância."),

    DM("Deficiência Mental",
            "Caracteriza-se por alterações de comportamento que impedem sua interação social, "
                    + "podendo manifestar-se em diferentes fases da vida."),

    DA("Deficiência Auditiva",
            "Caracteriza-se pela perda auditiva, "
                    + "total ou parcial, bilateral, "
                    + "podendo comprometer ou não a fala. "
                    + "Muitas pessoas surdas comunicam-se através da "
                    + "LIBRAS – Língua Brasileira de Sinais."),

    DV("Deficiência Visual",
            "Caracteriza-se por acentuada perda da acuidade visual, "
                    + "insuscetível de correção com o uso de óculos."),

    OR("Outro", "");

    private String descricao;
    private String tooltip;

    private Deficiencia (String descricao, String tooltip){
        this.descricao = descricao;
        this.tooltip = tooltip;
    }
}
