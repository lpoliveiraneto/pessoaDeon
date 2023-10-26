package com.pessoaDeon.domain.repository.natureza;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.mensagem.TituloAviso;

public interface AvisoNaturezaRepository extends JpaRepository<TituloAviso, Integer> {

	List<TituloAviso> findByNaturezaDeonIdNatureza(Integer id);

}
