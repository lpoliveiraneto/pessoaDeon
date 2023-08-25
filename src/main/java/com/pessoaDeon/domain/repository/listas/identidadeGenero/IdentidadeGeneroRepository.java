package com.pessoaDeon.domain.repository.listas.identidadeGenero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.listas.IdentidadeGenero;

@Repository
public interface IdentidadeGeneroRepository extends JpaRepository<IdentidadeGenero, Integer> {

}
