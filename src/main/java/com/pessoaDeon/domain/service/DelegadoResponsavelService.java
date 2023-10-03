package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.DelegadoResponsavel;
import com.pessoaDeon.domain.repository.DelegadoResponsavelRepository;

@Service
public class DelegadoResponsavelService {

	@Autowired
	private DelegadoResponsavelRepository delegadoRepository;
	
	public DelegadoResponsavel getDelegadoResponsavel() {
		return delegadoRepository.findByStatusIsTrue();
	}
}
