package com.pessoaDeon.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pessoaDeon.domain.model.enumeration.CorPele;
import com.pessoaDeon.domain.model.enumeration.Deficiencia;
import com.pessoaDeon.domain.model.enumeration.Escolaridade;
import com.pessoaDeon.domain.model.enumeration.IdentidadeGenero;
import com.pessoaDeon.domain.model.enumeration.OrientacaoSexual;
import com.pessoaDeon.domain.model.enumeration.Sexo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "fk_profissao")
//    private Profissao profissao;

    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;
}
