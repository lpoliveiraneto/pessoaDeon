package com.pessoaDeon.domain.repository.envolvido;

import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoParticipacaoRepository extends JpaRepository<TipoParticipacao, Integer> {

    Optional<TipoParticipacao> findByDescricao(String descricao);

    Optional<TipoParticipacao>findByValor(String valor);
}
