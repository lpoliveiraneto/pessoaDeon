package com.pessoaDeon.domain.repository.listas.bairro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.listas.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer> {

	List<Bairro> findByCidadeIdCidade(Integer idCidade);


	Bairro findByCidadeIdCidadeAndDescricao(Integer idCidade, String descricao);

}
