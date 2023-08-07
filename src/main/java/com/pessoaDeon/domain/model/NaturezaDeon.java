package com.pessoaDeon.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "natureza_deon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaturezaDeon implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_natureza")
    private Integer idNatureza;

    @NotNull
    @Column(name = "nome_natureza")
    private String nome;
    
    @NotNull
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "path_svg" )
    private String pathSvg;
    
    @NotNull
    @Column(name = "status_natureza")
    private Boolean status;

    @Column(name = "fk_natureza_sigma")
    private Integer fkNaturezaSigma;
}
