package com.pessoaDeon.domain.repository.bo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.bo.EnderecoLocalFato;

@Repository
public interface EnderecoLocalFatoRepository extends JpaRepository<EnderecoLocalFato, Integer> {

}
