package com.pessoaDeon.model;

import com.pessoaDeon.model.enumeration.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="pessoa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    private String nome;

    @NotNull
    @Size(max=255)
    private String nomeMae;

    @Size(max=255)
    private String nomePai;

    @Size(max = 50)
    private String alcunha;

    @NotNull
    private String cpf;

    @NotNull
    private LocalDateTime dataNascimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Size(max=50)
    private String nomeSocial;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrientacaoSexual orientacaoSexual;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IdentidadeGenero identidadeGenero;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Deficiencia deficiencia;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CorPele corPele;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pais")
    private Pais pais;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estado")
    private Estado estadoNaturalidade;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cidade")
    private Cidade cidadeNaturalidade;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> email;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profissao")
    private Profissao profissao;

    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;
}
