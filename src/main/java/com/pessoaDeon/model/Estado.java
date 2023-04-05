package com.pessoaDeon.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="estado")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    private String descricao;

    private String uf;
}
