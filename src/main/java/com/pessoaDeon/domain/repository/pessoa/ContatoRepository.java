package com.pessoaDeon.domain.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.pessoa.Telefone;

@Repository
public interface ContatoRepository extends JpaRepository<Telefone, Integer> {

}
