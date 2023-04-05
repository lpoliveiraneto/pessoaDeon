package com.pessoaDeon.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private int idEndereco;

    private int numero;

    private String referencia;

    private boolean atual;
}
