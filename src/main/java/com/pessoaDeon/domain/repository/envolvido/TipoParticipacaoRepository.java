package com.pessoaDeon.domain.repository.envolvido;

import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoParticipacaoRepository extends JpaRepository<TipoParticipacao, Integer> {
}
