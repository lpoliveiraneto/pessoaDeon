package com.pessoaDeon.domain.repository.pecas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.pecas.Peca;

public interface PecaRepository extends JpaRepository<Peca, Integer>{
    
}
