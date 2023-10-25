package com.pessoaDeon.domain.model.dto.analista;

import com.pessoaDeon.domain.model.analista.Cargo;
import com.pessoaDeon.domain.model.analista.Funcao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AnalistaRequest(
        String nome,
        @NotEmpty
        @NotBlank
        String cpf,
        Cargo cargo,
        Funcao funcao,
        String matricula,
        String senha,
        String assinatura
) {
}
