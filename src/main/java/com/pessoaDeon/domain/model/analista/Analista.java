package com.pessoaDeon.domain.model.analista;

import com.pessoaDeon.domain.model.pessoa.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Analista {

    @Id
    private Integer idAnalista;

    @OneToOne
    private Pessoa pessoa;

    private String cargo;

    private String funcao;

    private String matricula;

    private Integer fkSigma;


}
