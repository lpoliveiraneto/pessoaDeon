package com.pessoaDeon.domain.repository.natureza;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.natureza.NaturezaDeon;

@Repository
public interface NaturezaDeonRepository extends JpaRepository<NaturezaDeon, Integer> {

	Boolean existsByNaturezaSigma(Integer idNatureza);
	
	List<NaturezaDeon> findByStatusIsTrue();
}
