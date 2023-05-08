package com.pessoaDeon.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="email")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_email;

    private String email;

    private Boolean atual;

    @ManyToOne
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;

}
