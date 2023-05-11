package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.Telefone;

@Repository
public interface ContatoRepository extends JpaRepository<Telefone, Integer> {

}
