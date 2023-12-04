package com.pessoaDeon.domain.model.analista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "resposta_analise_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaAnaliseUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta_analise_usuario")
    private Integer idRespostaAnaliseUsuario;

    private String descricao;

    private boolean status;
}
