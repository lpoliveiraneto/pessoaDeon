package com.pessoaDeon.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "natureza_sigma")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NaturezaSigma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_natureza")
    private Integer idNaturezaSigma;

    @NotNull
    @Column(name = "codigo")
    private String codigo;
    
    @NotNull
    @Column(name = "glossario")
    private String glossario;
    
    @NotNull
    private String nome;
    
    @NotNull
    @Column(name = "tipo_natureza")
    private String tipoNatureza;
}
