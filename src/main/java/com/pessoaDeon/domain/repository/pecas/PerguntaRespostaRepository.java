package com.pessoaDeon.domain.repository.pecas;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pessoaDeon.domain.model.pecas.PerguntaResposta;
import com.pessoaDeon.domain.model.pecas.TipoPerguntaPeca;
import com.pessoaDeon.domain.model.pecas.TipoRespostaPeca;

@Repository
public interface PerguntaRespostaRepository extends JpaRepository<PerguntaResposta, Integer>{

    List<PerguntaResposta> findByPerguntaAndAtivaIsTrueOrderByIdAsc(TipoPerguntaPeca pergunta);

    List<PerguntaResposta> findByPerguntaId(Integer idPergunta);

    List<TipoRespostaPeca> findRespostasByPerguntaId(Integer perguntaId);

    PerguntaResposta findPerguntaRespostaById(Integer perguntaId);
}
