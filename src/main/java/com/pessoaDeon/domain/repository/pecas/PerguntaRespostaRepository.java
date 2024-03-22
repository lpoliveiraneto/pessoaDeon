package com.pessoaDeon.domain.repository.pecas;

import java.util.List;

import com.pessoaDeon.domain.model.pecas.formulario_risco.PerguntaRespostaFormularioRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pessoaDeon.domain.model.pecas.formulario_risco.TipoPerguntaFormularioRisco;
import com.pessoaDeon.domain.model.pecas.formulario_risco.TipoRespostaFormularioRisco;

@Repository
public interface PerguntaRespostaRepository extends JpaRepository<PerguntaRespostaFormularioRisco, Integer>{

    List<PerguntaRespostaFormularioRisco> findByPerguntaAndAtivaIsTrueOrderByIdAsc(TipoPerguntaFormularioRisco pergunta);

    List<PerguntaRespostaFormularioRisco> findByPerguntaId(Integer idPergunta);

    List<TipoRespostaFormularioRisco> findRespostasByPerguntaId(Integer perguntaId);

    PerguntaRespostaFormularioRisco findPerguntaRespostaById(Integer perguntaId);
    
//    @Query("SELECT pr FROM PerguntaRespostaFormularioRisco pr WHERE pr.fk_pergunta= ?1 AND pr.fk_resposta = ?2")
//    PerguntaRespostaFormularioRisco findPerguntaidandRespotaId(Integer perguntaId, Integer respostaId);
    
    PerguntaRespostaFormularioRisco findByPerguntaIdAndRespostaIdAndAtivaIsTrue(Integer perguntaId, Integer respostaId);
}
