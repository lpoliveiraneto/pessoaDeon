package com.pessoaDeon.domain.repository.endereco;

import com.pessoaDeon.domain.model.endereco.Endereco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	Optional<Endereco> findByIdEndereco(Integer idEndereco);

	Endereco findByPessoaId(Integer idPessoa);
}
