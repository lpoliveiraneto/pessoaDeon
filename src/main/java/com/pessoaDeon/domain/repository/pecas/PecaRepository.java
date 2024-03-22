package com.pessoaDeon.domain.repository.pecas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.pecas.Peca;
import com.pessoaDeon.domain.model.pecas.TipoPeca;

public interface PecaRepository extends JpaRepository<Peca, Integer>{

	Peca findByBoIdBoAndTipoPeca(Integer bo, TipoPeca formRisco);
    
}
