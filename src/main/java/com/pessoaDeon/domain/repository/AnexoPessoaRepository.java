package com.pessoaDeon.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.AnexoPessoa;

public interface AnexoPessoaRepository extends JpaRepository<AnexoPessoa, Integer> {

	List<AnexoPessoa> findByPessoaId(Integer idPessoa);
	
}
