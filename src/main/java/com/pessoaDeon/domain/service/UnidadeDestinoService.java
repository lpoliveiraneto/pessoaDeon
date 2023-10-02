package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.listas.UnidadeDestino;
import com.pessoaDeon.domain.repository.listas.UnidadeDestinoRepository;

@Service
public class UnidadeDestinoService {
	
	@Autowired
	private UnidadeDestinoRepository unidadeDestinoRepository;
	
	public UnidadeDestino getUnidadeDestino() {
		return unidadeDestinoRepository.findBySigla("DEON");
	}

}
