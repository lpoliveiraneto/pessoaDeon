package com.pessoaDeon.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    private String descricao;

    private String uf;
}
