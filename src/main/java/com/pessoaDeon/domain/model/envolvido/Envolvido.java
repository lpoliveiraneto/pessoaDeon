package com.pessoaDeon.domain.model.envolvido;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.model.listas.Deficiencia;
import com.pessoaDeon.domain.model.listas.Escolaridade;
import com.pessoaDeon.domain.model.listas.Estado;
import com.pessoaDeon.domain.model.listas.EstadoCivil;
import com.pessoaDeon.domain.model.listas.IdentidadeGenero;
import com.pessoaDeon.domain.model.listas.OrientacaoSexual;
import com.pessoaDeon.domain.model.listas.Pais;
import com.pessoaDeon.domain.model.listas.Profissao;
import com.pessoaDeon.domain.model.listas.Raca;
import com.pessoaDeon.domain.model.listas.Sexo;
import com.pessoaDeon.domain.model.pessoa.Pessoa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDate dataNascimento;

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
    
    @OneToOne
    @JoinColumn(name = "fk_endereco_envolvido")
    private EnderecoEnvolvido enderecoEnvolvido;
}
