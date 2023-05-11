package com.pessoaDeon.domain.model;

import java.io.Serializable;

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

    private String bairro;

    private String complemento;

    private String localidade;

    private String uf;

    private String ibge;

    private String gia;

    private String ddd;

    private String siafi;
    
    @Transient
    private Boolean erro = false;

}
