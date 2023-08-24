package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.EnderecoLocalFato;

@Repository
public interface EnderecoLocalFatoRepository extends JpaRepository<EnderecoLocalFato, Integer> {

}
