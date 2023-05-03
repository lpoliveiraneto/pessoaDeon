package com.pessoaDeon.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.Cidade;
import com.pessoaDeon.domain.repository.listas.cidade.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	CidadeRepository cidadeRepository;

	public List<Cidade> findByEstadoIdEstado(Integer idEstado) {
		return cidadeRepository.findByEstadoIdEstadoAndCodigoIbgeIsNotNull(idEstado);
	}
	
}
