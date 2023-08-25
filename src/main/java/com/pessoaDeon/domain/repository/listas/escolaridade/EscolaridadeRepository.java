package com.pessoaDeon.domain.repository.listas.escolaridade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.listas.Escolaridade;

@Repository
public interface EscolaridadeRepository extends JpaRepository<Escolaridade, Integer> {

}
