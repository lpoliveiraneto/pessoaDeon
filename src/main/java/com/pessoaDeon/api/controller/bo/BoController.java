package com.pessoaDeon.api.controller.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.model.dto.BoDtoResponse;
import com.pessoaDeon.domain.service.BoService;


@RestController
@RequestMapping("/api/v1/bo")
public class BoController {

	@Autowired
	private BoService boService;
	
	@PostMapping("/salvarBo")
	public ResponseEntity<Object> salvarOcorrencia(@RequestBody BoDto bo){
		return ResponseEntity.ok().body(boService.salvar(bo));
	}
	
	@GetMapping("/buscarPorId/{idBo}")
	public BoDtoResponse buscarBoPorId(@PathVariable(value = "idBo" ) Integer idBo){
		return boService.buscarBoPorId(idBo); 
	}
}
