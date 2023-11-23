package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.FotoPerfil;

public interface FotoPerfilRepository extends JpaRepository<FotoPerfil, Integer> {

	FotoPerfil findByPessoa(Integer idPessoa);
	
}
