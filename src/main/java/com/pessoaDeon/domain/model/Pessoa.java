package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="pessoa")
//@Table(name="pessoa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_pessoa")
    private Integer id;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

//    @Enumerated(EnumType.STRING)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_sexo")
    private Sexo sexo;

    @Size(max=50)
    private String nomeSocial;

    @NotNull
    @JoinColumn(name = "fk_orientacao_sexual")
    @ManyToOne
    private OrientacaoSexual orientacaoSexual;

    @NotNull
    @JoinColumn(name = "fk_identidade_genero")
    @ManyToOne
    private IdentidadeGenero identidadeGenero;

    @NotNull
    @JoinColumn(name = "fk_deficiencia")
    @ManyToOne
    private Deficiencia deficiencia;

    @NotNull
    @JoinColumn(name = "fk_raca")
    @ManyToOne
    private Raca corPele;

    @NotNull
    //@ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pais")
    private Pais pais;

    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_estado")
    private Estado estadoNaturalidade;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_cidade")
    private Cidade cidadeNaturalidade;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> email;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profissao")
    private Profissao profissao;
    
    @NotNull
    @JoinColumn(name = "fk_estado_civil")
    @ManyToOne
    private EstadoCivil estadoCivil;

    @NotNull
    @JoinColumn(name = "fk_escolaridade")
    @ManyToOne
    private Escolaridade escolaridade;
}
