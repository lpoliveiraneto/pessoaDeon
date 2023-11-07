package com.pessoaDeon.domain.repository.analista;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.analista.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
