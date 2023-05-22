package com.pessoaDeon.domain.repository.listas.estadoCivil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.EstadoCivil;


@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {

}
