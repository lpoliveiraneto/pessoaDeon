package com.pessoaDeon.domain.repository;

import com.pessoaDeon.domain.model.Endereco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	Optional<Endereco> findByIdEndereco(Integer idEndereco);
}
