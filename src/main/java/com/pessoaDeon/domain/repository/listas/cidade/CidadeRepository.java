package com.pessoaDeon.domain.repository.listas.cidade;

import com.pessoaDeon.domain.model.Cidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	List<Cidade> findByEstadoIdEstado(Integer idEstado);

	List<Cidade> findByEstadoIdEstadoAndCodigoIbgeIsNotNull(Integer idEstado);
}
