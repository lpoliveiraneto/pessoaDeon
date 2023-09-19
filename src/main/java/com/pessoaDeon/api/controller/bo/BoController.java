package com.pessoaDeon.api.controller.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.model.dto.BoDtoResponse;
import com.pessoaDeon.domain.model.dto.BosPessoaResponseDto;
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
	
	@GetMapping("/buscar")
	public Page<BosPessoaResponseDto> buscar (@RequestParam(name = "idPessoa") Integer idPessoa, 
			@PageableDefault(size = 10, page = 0, sort = "idBo", direction = Direction.ASC) Pageable pageable){
		return boService.buscarPessoa(idPessoa, pageable);
	}
}
