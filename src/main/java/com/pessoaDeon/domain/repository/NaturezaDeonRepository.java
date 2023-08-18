package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.NaturezaDeon;

@Repository
public interface NaturezaDeonRepository extends JpaRepository<NaturezaDeon, Integer> {

	Boolean existsByNaturezaSigma(Integer idNatureza);
}
