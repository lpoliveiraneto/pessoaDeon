package com.pessoaDeon.domain.repository.listas.raca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Integer> {

}
