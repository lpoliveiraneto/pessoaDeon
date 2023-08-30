package com.pessoaDeon.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.bo.EnderecoLocalFato;
import com.pessoaDeon.domain.model.bo.Protocolo;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.model.dto.BoDtoResponse;
import com.pessoaDeon.domain.model.dto.NaturezaDto;
import com.pessoaDeon.domain.repository.bo.BoRepository;
import com.pessoaDeon.domain.repository.bo.EnderecoLocalFatoRepository;
import com.pessoaDeon.domain.repository.bo.ProtocoloRepository;


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
		protocolo.setDataRegistro(LocalDateTime.now());
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
	
	@Transactional
	public BoDtoResponse buscarBoPorId(Integer idBo) {
		BoDtoResponse response = new BoDtoResponse();
		EnderecoLocalFato endereco = enderecoLocalFatoRepository.findById(idBo).get();
		BoDeon bo = boRepository.findById(idBo).get();
		Protocolo protocolo = protocoloRepository.findById(idBo).get();
		response.setDataFato(bo.getDataFato());
		response.setHoraFato(bo.getHoraFato());
		response.setRelato(bo.getRelato());
		response.setLogradouro(endereco.getLogradouro());
		response.setBairroDescricao(endereco.getBairro().getDescricao());
		response.setComplemento(endereco.getComplemento());
		response.setCidadeDescricao(endereco.getCidade().getDescricao());
		response.setEstadoDescricao(endereco.getEstado().getDescricao());
		response.setCep(endereco.getCep());
		response.setNumeroLocal(endereco.getNumeroLocal());
		response.setTipoLocal(endereco.getTipoLocal().getNome());
		response.setProtocolo(protocolo.getNumero());
		List<NaturezaDto> naturezaDto = new ArrayList<>();
		bo.getListaNaturezas().forEach(n -> {
			NaturezaDto nt = new NaturezaDto();
			BeanUtils.copyProperties(n.getNaturezaDeon(), nt);
			naturezaDto.add(nt);
		});
		response.setListaNatureza(naturezaDto);
		return response;
	}
}

