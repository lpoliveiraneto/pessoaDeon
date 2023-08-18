package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.NaturezaDeon;
import com.pessoaDeon.domain.model.NaturezaSigma;
import com.pessoaDeon.domain.model.dto.NaturezaRequestDto;
import com.pessoaDeon.domain.repository.NaturezaDeonRepository;
import com.pessoaDeon.domain.repository.NaturezaSigmaRepository;

@Service
public class NaturezaService {

	@Autowired
	private NaturezaSigmaRepository naturezaRepository;
	
	@Autowired
	private NaturezaDeonRepository repository;
	
	public Page<?> listNatureza(Pageable pageable) {
		return naturezaRepository.findAll(pageable);
	}

	@Transactional
	public Page<NaturezaSigma> filtroNome(String nome, Pageable pageable){
		return naturezaRepository.findAllByNomeContains(nome, pageable);
	}

	@Transactional
	public Page<NaturezaSigma> filtroGlossario(String glossario, Pageable pageable){
		return naturezaRepository.findAllByGlossarioContains(glossario, pageable);
	}
	
	@Transactional
	public ResponseEntity<?> salvar(NaturezaRequestDto dto) {

		NaturezaDeon deon =  new NaturezaDeon();
		deon.setNome(!dto.getNome().isEmpty() ? dto.getNome() : null);
		deon.setDescricao(dto.getGlossario());
		deon.setCodigo(dto.getCodigo());
		deon.setTipoNatureza(dto.getTipoNatureza());
		deon.setPathSvg(dto.getPathSvg());
		deon.setNaturezaSigma(dto.getIdNaturezaSigma());
		deon.setStatus(true);
		
		return ResponseEntity.ok(repository.save(deon));
	}
	
	public NaturezaSigma buscarNaturezaSigma(Integer idNaturezaSigma){
		NaturezaSigma nat = naturezaRepository.findById(idNaturezaSigma).orElseThrow(() -> 
			new RuntimeException("Natureza não encontrada = " + idNaturezaSigma));
		return nat;
	}
	
	public Boolean existeNatureza(NaturezaRequestDto dto) {
		return repository.existsByNaturezaSigma(dto.getIdNaturezaSigma());
	}
}
