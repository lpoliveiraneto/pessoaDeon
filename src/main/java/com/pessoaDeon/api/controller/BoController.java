package com.pessoaDeon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.BoDeon;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.service.BoService;

@RestController
@RequestMapping("/api/v1/bo")
public class BoController {

	@Autowired
	private BoService boService;
	
	@PostMapping("/salvarBo")
	public ResponseEntity<BoDeon> salvarOcorrencia(@RequestBody BoDto bo){
		return ResponseEntity.ok().body(boService.salvar(bo));
	}
}
