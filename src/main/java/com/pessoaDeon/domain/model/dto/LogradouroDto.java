package com.pessoaDeon.domain.model.dto;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogradouroDto {

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