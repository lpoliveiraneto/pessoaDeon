package com.pessoaDeon.domain.model;

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
    private Integer idEndereco;

    private String numero;

    private String referencia;

    private Boolean atual;

    @ManyToOne
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "fk_logradouro")
    private Logradouro logradouro;
}
