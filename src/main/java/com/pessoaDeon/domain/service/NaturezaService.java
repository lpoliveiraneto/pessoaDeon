package com.pessoaDeon.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.dto.   NaturezaRequestDto;
import com.pessoaDeon.domain.model.natureza.NaturezaDeon;
import com.pessoaDeon.domain.model.natureza.NaturezaSigma;
import com.pessoaDeon.domain.repository.natureza.NaturezaDeonRepository;
import com.pessoaDeon.domain.repository.natureza.NaturezaSigmaRepository;

@Service
public class NaturezaService {

	@Autowired
	private NaturezaSigmaRepository naturezaRepository;
	
	@Autowired
	private NaturezaDeonRepository repository;
	
	public Page<NaturezaSigma> listNatureza(Pageable pageable) {
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
	//		NaturezaSigma nat = naturezaRepository.findById(idNaturezaSigma).orElseThrow(() ->
	//			new RuntimeException("Natureza não encontrada = " + idNaturezaSigma));
	//		return nat;
		NaturezaSigma nat = naturezaRepository.findById(idNaturezaSigma).get();
		return nat;
	}
	
	public Boolean existeNatureza(NaturezaRequestDto dto) {
		return repository.existsByNaturezaSigma(dto.getIdNaturezaSigma());
	}

	public ResponseEntity<?> listarNaturezaDeonAtiva() {
		List<NaturezaDeon> naturezas = repository.findByStatusIsTrue();
		if (!naturezas.isEmpty()) {
			return ResponseEntity.ok().body(naturezas);			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe naturezas ativas");
		}
	}
}
