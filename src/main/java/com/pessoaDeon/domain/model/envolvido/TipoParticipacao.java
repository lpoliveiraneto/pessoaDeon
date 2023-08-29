package com.pessoaDeon.domain.model.envolvido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoParticipacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoParticipacao;

    private String descricao;
}
