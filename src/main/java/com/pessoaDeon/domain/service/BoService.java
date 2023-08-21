package com.pessoaDeon.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.BoDeon;
import com.pessoaDeon.domain.model.EnderecoLocalFato;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.repository.BoRepository;
import com.pessoaDeon.domain.repository.EnderecoLocalFatoRepository;

import jakarta.transaction.Transactional;

@Service
public class BoService {

	@Autowired
	private BoRepository boRepository;	 
	
	@Autowired
	private EnderecoLocalFatoRepository enderecoLocalFatoRepository;
	
	@Transactional
	public Object salvar(BoDto bo) {
		BoDeon boletim = new BoDeon();
		boletim.setDataFato(bo.getDataFato());
		boletim.setHoraFato(bo.getHoraFato());
		boletim.setProtocolo(bo.getProtocolo());
		boletim.setDataRegistro(LocalDateTime.now()); 
		boletim.setRelato(bo.getRelato());
		boletim.setRelatoEditado(bo.getRelatoEditado());
		var boSave = boRepository.save(boletim);
		
		if(boletim != null) {
			this.salvarEnd(bo, boSave);
		}
		return boSave;
	}
	
	private EnderecoLocalFato salvarEnd(BoDto bo, BoDeon deon) {
		EnderecoLocalFato end = new EnderecoLocalFato();
		end.setCep(bo.getCep());
		end.setComplemento(bo.getComplemento());
		end.setLogradouro(bo.getLogradouro());
		end.setNumeroLocal(bo.getNumeroLocal());
		end.setBairro(bo.getBairro());
		end.setCidade(bo.getCidade());
		end.setEstado(bo.getEstado());
		end.setTipoLocal(bo.getTipoLocal());
		end.setBo(deon);
		return enderecoLocalFatoRepository.save(end);
	}

}
