package com.pessoaDeon.domain.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.BoDeon;
import com.pessoaDeon.domain.model.EnderecoLocalFato;
import com.pessoaDeon.domain.model.Protocolo;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.repository.BoRepository;
import com.pessoaDeon.domain.repository.EnderecoLocalFatoRepository;
import com.pessoaDeon.domain.repository.ProtocoloRepository;


@Service
public class BoService {

	@Autowired
	private BoRepository boRepository;	 
	
	@Autowired
	private EnderecoLocalFatoRepository enderecoLocalFatoRepository;
	
	@Autowired
	private ProtocoloRepository protocoloRepository;
	
	@Transactional
	public Object salvar(BoDto bo) {
		BoDeon boletim = new BoDeon();
		var dataRegistro = Calendar.getInstance().getTime();
		
		boletim.setDataFato(bo.getDataFato());
		boletim.setHoraFato(bo.getHoraFato());
		boletim.setDataRegistro(dataRegistro); 
		boletim.setRelato(bo.getRelato());
		boletim.setRelatoEditado(bo.getRelatoEditado());
		
		var boSave = boRepository.saveAndFlush(boletim);
		
		if(boSave != null) {
			this.salvarEnd(bo, boSave);
			this.salvarProtocolo(boSave);
		}
		return boSave;
	}
	
	@Transactional
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
	
	@Transactional
	private Protocolo salvarProtocolo(BoDeon bo) {
		
		Protocolo protocolo = new Protocolo();
		protocolo.setBo(bo);
		protocolo.setNumero(gerarProtocolo(bo));
		protocolo.setDataRegistro(new Date());
		return protocoloRepository.save(protocolo);
	}
	
	private String gerarProtocolo(BoDeon ocorrencia) {

		StringBuilder protocolo = new StringBuilder();
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(ocorrencia.getDataRegistro());		
		
		protocolo.append(ocorrencia.getIdBo());
		protocolo.append(calendario.get(Calendar.HOUR));
		protocolo.append(calendario.get(Calendar.MONTH));
		protocolo.append(calendario.get(Calendar.YEAR));
		protocolo.append(calendario.get(Calendar.SECOND));
		return protocolo.toString();
	}
}

