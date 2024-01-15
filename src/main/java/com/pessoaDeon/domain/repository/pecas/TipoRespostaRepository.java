package com.pessoaDeon.domain.repository.pecas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.pecas.TipoRespostaPeca;

@Repository
public interface TipoRespostaRepository extends JpaRepository<TipoRespostaPeca, Integer>{
    
}
