package com.pessoaDeon.domain.model.analista;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Analista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnalista;

    @OneToOne
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "fk_cargo")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "fk_funcao")
    private Funcao funcao;

    private String matricula;

    @Column(name = "fk_func_sigma")
    private Integer funcionarioSigma;

    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_cadastro;

    private Boolean status = false;



}
