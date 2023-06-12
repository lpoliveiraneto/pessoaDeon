package com.pessoaDeon.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="logradouro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Logradouro implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logradouro")
    private Integer idLogradouro;

    private String cep;

    private String logradouro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "fk_bairro")
    private Bairro bairro;

    private String complemento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "fk_cidade")
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "fk_estado")
    private Estado estado;

    private String ibge;
    
    private Boolean cepDesconhecido = false;
    
    @Transient
    private Boolean erro = false;

}
