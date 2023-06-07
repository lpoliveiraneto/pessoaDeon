package com.pessoaDeon.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.BoDeon;
import com.pessoaDeon.domain.model.Logradouro;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.repository.BoRepository;

import jakarta.transaction.Transactional;

@Service
public class BoService {

	@Autowired
	private BoRepository boRepository;	 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LogradouroService logradouroService;

	@Transactional
	public BoDeon salvar(BoDto bo) {
		BoDeon boletim = new BoDeon();
		
		var logradouro = modelMapper.map(bo, Logradouro.class);
		if(logradouro.getCep() != null && !logradouro.getCep().isEmpty()) {
			logradouroService.getByCep(logradouro.getCep());
		}
		
		boletim.setDataFato(bo.getDataFato());
		boletim.setHoraFato(bo.getHoraFato());
		boletim.setNumeroLocal(bo.getNumeroLocal());
		boletim.setCep(bo.getCep());
		boletim.setComplemento(bo.getComplemento());
		boletim.setPontoReferencia(bo.getPontoReferencia());
		boletim.setLogradouro(bo.getLogradouro());
		boletim.setFkEstado(bo.getFkEstado());
		boletim.setFkCidade(bo.getFkCidade());
		boletim.setFkBairro(bo.getFkBairro());
		boletim.setTipoLocal(bo.getTipoLocal());
		boletim.setRelato(bo.getRelato());
		
		return boRepository.save(boletim);
	}

}
