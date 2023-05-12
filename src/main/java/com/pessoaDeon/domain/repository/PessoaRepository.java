package com.pessoaDeon.domain.repository;

import com.pessoaDeon.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    @Override
    Optional<Pessoa> findById(Integer idPessoa);

    Optional<Pessoa> findByCpf(String cpf);

}