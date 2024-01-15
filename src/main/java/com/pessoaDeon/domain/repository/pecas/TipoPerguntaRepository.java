package com.pessoaDeon.domain.repository.pecas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.pecas.TipoPeca;
import com.pessoaDeon.domain.model.pecas.TipoPerguntaPeca;

@Repository
public interface TipoPerguntaRepository extends JpaRepository<TipoPerguntaPeca, Integer>{

    List<TipoPerguntaPeca> findByTipoPecaAndAtivoIsTrue(TipoPeca tp);

    List<TipoPerguntaPeca> findByTipoPecaAndAtivoIsTrueAndBlocoIdOrderByIdAsc(TipoPeca tp, Integer bloco);
}
