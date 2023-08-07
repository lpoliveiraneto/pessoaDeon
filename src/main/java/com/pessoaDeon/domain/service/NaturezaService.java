package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.NaturezaSigma;
import com.pessoaDeon.domain.repository.NaturezaRepository;

@Service
public class NaturezaService {

	@Autowired
	private NaturezaRepository naturezaRepository;
	
	public Page<?> listNatureza(Pageable pageable) {
		return naturezaRepository.findAll(pageable);
	}

	@Transactional
	public Page<NaturezaSigma> filtroNome(String nome, Pageable pageable){
		return naturezaRepository.findAllByNomeContains(nome, pageable);
	}
	
}
