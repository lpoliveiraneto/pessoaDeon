package com.pessoaDeon.domain.repository.natureza;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.mensagem.DescricaoTitulo;

public interface DescricaoAvisoNaturezaRepository extends JpaRepository<DescricaoTitulo, Integer> {
}
