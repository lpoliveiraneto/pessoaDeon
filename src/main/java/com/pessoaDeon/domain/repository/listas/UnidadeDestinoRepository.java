package com.pessoaDeon.domain.repository.listas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.listas.UnidadeDestino;

public interface UnidadeDestinoRepository extends JpaRepository<UnidadeDestino, Integer>{

	UnidadeDestino findBySigla(String sigla);

}
