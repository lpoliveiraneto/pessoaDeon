package com.pessoaDeon.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.BoDeon;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.repository.BoRepository;

import jakarta.transaction.Transactional;

@Service
public class BoService {

	@Autowired
	private BoRepository boRepository;	 
	
	@Transactional
	public BoDeon salvar(BoDto bo) {
		BoDeon boletim = new BoDeon();

		boletim.setDataFato(bo.getDataFato());
		boletim.setHoraFato(bo.getHoraFato());
		boletim.setProtocolo(bo.getProtocolo());
		boletim.setDataRegistro(LocalDateTime.now()); 
		boletim.setRelato(bo.getRelato());
		boletim.setRelatoEditado(bo.getRelatoEditado());
		
		return boRepository.save(boletim);
	}

}
