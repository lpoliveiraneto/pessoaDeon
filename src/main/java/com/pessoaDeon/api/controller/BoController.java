package com.pessoaDeon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.BoDeon;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.service.BoService;
import com.pessoaDeon.domain.service.NaturezaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/bo")
public class BoController {

	@Autowired
	private BoService boService;
	
	@Autowired
	private NaturezaService naturezaService;
	
	@PostMapping("/salvarBo")
	public ResponseEntity<BoDeon> salvarOcorrencia(@RequestBody BoDto bo){
		return ResponseEntity.ok().body(boService.salvar(bo));
	}
	
	@GetMapping("/listNaturezaSigma")
	public ResponseEntity<?> lista(@PageableDefault(size = 10, page = 0, sort = "nome", direction = Direction.ASC) Pageable pageable){
		var naturezas = naturezaService.listNatureza( pageable);
		if(!naturezas.isEmpty()) {
			return new ResponseEntity<>(naturezas, HttpStatus.OK);			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<?> filtros(@RequestParam("nome") String nome, Pageable pageable){
		return ResponseEntity.ok(naturezaService.filtroNome(nome, pageable));
	}
}
