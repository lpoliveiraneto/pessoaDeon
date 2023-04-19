package com.pessoaDeon.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cidade")
    private int idCidade;

    private String descricao;

    @Column(name = "cod_ibge")
    private int codigoIbge;

    @Column(name = "eh_capital")
    private boolean capital;

    private float latitude;

    private float longitude;
}
