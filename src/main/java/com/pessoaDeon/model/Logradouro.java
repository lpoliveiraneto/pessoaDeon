package com.pessoaDeon.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="logradouro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logradouro")
    private int idLogradouro;

    private String cep;

    private String logradouro;

    private String bairro;

    private String complemento;

    private String localidade;

    private String uf;

    private String ibge;

    private String gia;

    private String ddd;

    private String siafi;

}
