package com.pessoaDeon.domain.model.dto;

import com.pessoaDeon.domain.model.listas.Bairro;
import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.model.listas.Estado;
import com.pessoaDeon.domain.model.listas.TipoLocal;
import lombok.Getter;

@Getter
public class EnderecoEnvolvidoDto {

    private String cep;
    private Estado estado;
    private Cidade cidade;
    private String logradouro;
    private Bairro bairro;
    private String numero;
    private TipoLocal tipoLocal;
    private String complemento;
    private String referencia;
}
