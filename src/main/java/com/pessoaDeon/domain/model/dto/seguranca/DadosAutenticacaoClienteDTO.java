package com.pessoaDeon.domain.model.dto.seguranca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DadosAutenticacaoClienteDTO {

    private String email;
    private String senha;
}
