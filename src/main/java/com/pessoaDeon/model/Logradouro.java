package com.pessoaDeon.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="logradouro")
@Data
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logradouro")
    private int idLogradouro;

    private String cep;

    private String logradouro;

    private String bairro;

    private String complemento;

}
