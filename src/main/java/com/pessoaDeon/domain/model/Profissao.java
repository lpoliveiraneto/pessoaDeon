package com.pessoaDeon.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="profissao")
@Data
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfissao;

    private String descricao;
}
