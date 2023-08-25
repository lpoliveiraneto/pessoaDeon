package com.pessoaDeon.domain.repository.natureza;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.natureza.NaturezaSigma;

@Repository
public interface NaturezaSigmaRepository extends JpaRepository<NaturezaSigma, Integer> {

	Page<NaturezaSigma> findAll(Pageable pageable);
	
	Page<NaturezaSigma> findAllByNomeContains(String nome, Pageable pageable);
	
	Page<NaturezaSigma> findAllByGlossarioContains(String glossario, Pageable pageable);

}
