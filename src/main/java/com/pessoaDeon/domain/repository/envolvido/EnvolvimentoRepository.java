package com.pessoaDeon.domain.repository.envolvido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.envolvido.Envolvimento;

@Repository
public interface EnvolvimentoRepository extends JpaRepository<Envolvimento, Integer> {
	
	List<Envolvimento> findByNaturezaBoBoIdBo(Integer idBo);

	Envolvimento findByNaturezaBoBoIdBoAndTipoParticipacaoValorOrNaturezaBoBoIdBoAndTipoParticipacaoValor(Integer idBo, String tipoParticipacao, Integer idBo2, String tipoParticipacao2);
}
