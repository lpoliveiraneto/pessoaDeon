package com.pessoaDeon.domain.model.envolvido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pessoaDeon.domain.model.listas.*;
import com.pessoaDeon.domain.model.pessoa.Email;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.pessoa.Telefone;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idEnvolvido")
public class Envolvido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvolvido;

    private String nome;
    private String nomeMae;
    private String nomePai;
    private String alcunha;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datanascimento;

    @ManyToOne
    @JoinColumn(name = "fk_sexo")
    private Sexo sexo;

    @Size(max=50)
    private String nomeSocial;

    @ManyToOne
    @JoinColumn(name = "fk_orientacao_sexual")
    private OrientacaoSexual orientacaoSexual;

    @ManyToOne
    @JoinColumn(name = "fk_identidade_genero")
    private IdentidadeGenero identidadeGenero;

    @ManyToOne
    @JoinColumn(name = "fk_deficiencia")
    private Deficiencia deficiencia;

    @ManyToOne
    @JoinColumn(name = "fk_raca")
    private Raca corPele;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pais")
    private Pais pais;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_estado")
    private Estado estadoNaturalidade;

    @ManyToOne
    @JoinColumn(name = "fk_cidade")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Cidade cidadeNaturalidade;

    private String email;

    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profissao")
    private Profissao profissao;

    @ManyToOne
    @JoinColumn(name = "fk_estado_civil")
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "fk_escolaridade")
    private Escolaridade escolaridade;

    private String numeroDocumento;

    @ManyToOne
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;
}
