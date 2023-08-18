package com.pessoaDeon.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
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

	@NotEmpty(message = "Nome não pode ser nulo e vazio")
    @Column(name = "nome")
    private String nome;
    
    @NotNull
    @Column(name = "codigo")
    private String codigo;
    
    @NotNull
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "path_svg" )
    private String pathSvg;
    
    @NotNull
    @Column(name = "tipo_natureza")
    private String tipoNatureza;
    
    @Column(name = "status")
    private Boolean status = false;

    @Column(name = "fk_natureza_sigma")
    private Integer naturezaSigma;
}
