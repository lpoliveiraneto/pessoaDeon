package com.pessoaDeon.domain.model.dto.analista;

import com.pessoaDeon.domain.model.analista.Cargo;
import com.pessoaDeon.domain.model.analista.Funcao;

public record AnalistaRequest(
        String nome,
        String cpf,
        Cargo cargo,
        Funcao funcao,
        String matricula,
        String senha
) {
}
