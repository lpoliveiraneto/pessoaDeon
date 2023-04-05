package com.pessoaDeon.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity(name="endereco")
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private int idEndereco;

    private int numero;

    private String referencia;

    private boolean atual;
}
