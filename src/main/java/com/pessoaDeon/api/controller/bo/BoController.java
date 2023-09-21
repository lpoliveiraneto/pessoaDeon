package com.pessoaDeon.api.controller.bo;

import com.pessoaDeon.domain.model.dto.BoEnvolvidosRequest;
import com.pessoaDeon.domain.model.dto.EnvolvidosRequestDto;
import com.pessoaDeon.domain.service.bo.BoDeonFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.model.dto.BoDtoResponse;
import com.pessoaDeon.domain.service.bo.BoService;
import com.pessoaDeon.domain.model.dto.BosPessoaResponseDto;



@RestController
@RequestMapping("/api/v1/bo")
public class BoController {

	@Autowired
	private BoService boService;

	@Autowired
	private BoDeonFactoryService boDeonFactoryService;
	
//	@PostMapping("/salvarBo")
//	public ResponseEntity<Object> salvarOcorrencia(@RequestBody BoDto bo){
//		return ResponseEntity.ok().body(boService.salvar(bo));
	//	}
	//
	@PostMapping("/salvarBo")
	public ResponseEntity salvarOcorrencia(@RequestBody BoEnvolvidosRequest boEnvolvidosRequest){
		System.out.println("passou por aqui");
		boDeonFactoryService.salvarBo(boEnvolvidosRequest.getBo(), boEnvolvidosRequest.getEnvolvidos());
		return ResponseEntity.ok().build();
	}
	@GetMapping("/buscarPorId/{idBo}")
	public BoDtoResponse buscarBoPorId(@PathVariable(value = "idBo" ) Integer idBo){
		return boService.buscarBoPorId(idBo); 
	}

	@GetMapping("/buscar")
	public Page<BosPessoaResponseDto> buscar (@RequestParam(name = "idPessoa") Integer idPessoa,
											  @PageableDefault(size = 10, page = 0, sort = "idBo", direction = Sort.Direction.ASC) Pageable pageable){
		return boService.buscarPessoa(idPessoa, pageable);
	}
}
