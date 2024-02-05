package com.pessoaDeon.domain.repository.pecas;

import java.util.List;

import com.pessoaDeon.domain.model.pecas.formulario_risco.TipoPerguntaFormularioRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPerguntaRepository extends JpaRepository<TipoPerguntaFormularioRisco, Integer>{

    List<TipoPerguntaFormularioRisco> findByAtivoIsTrueAndBlocoIdOrderByIdAsc(Integer bloco);
}
