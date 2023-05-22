package com.pessoaDeon.domain.repository.listas.deficiencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.Deficiencia;

@Repository
public interface DeficienciaRepository extends JpaRepository<Deficiencia, Integer> {

}
