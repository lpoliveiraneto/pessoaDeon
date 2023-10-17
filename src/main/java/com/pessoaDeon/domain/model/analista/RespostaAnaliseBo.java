package com.pessoaDeon.domain.model.analista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "resposta_analise_bo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaAnaliseBo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta_analise_bo")
    private Integer idRespostaAnalise;

    private String descricao;

    private boolean status;
}
