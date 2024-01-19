package com.pessoaDeon.domain.repository.pecas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.pecas.TipoPeca;
import com.pessoaDeon.domain.model.pecas.TituloRequerimentoMpu;

@Repository
public interface  TituloRequerimentoMpuRepository  extends JpaRepository<TituloRequerimentoMpu, Integer>{

    List<TituloRequerimentoMpu> findByTipoPecaAndStatusAtivoIsTrue(TipoPeca tp);
    
}
