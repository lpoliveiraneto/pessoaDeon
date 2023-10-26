package com.pessoaDeon.api.controller.bo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.dto.bo.BoDtoResponse;
import com.pessoaDeon.domain.model.dto.bo.BoEnvolvidosRequest;
import com.pessoaDeon.domain.model.dto.bo.BosPessoaResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.repository.bo.BoRepository;
import com.pessoaDeon.domain.service.bo.BoDeonFactoryService;
import com.pessoaDeon.domain.service.bo.BoService;



@RestController
@RequestMapping("/api/v1/bo")
public class BoController {

	@Autowired
	private BoService boService;

	@Autowired
	private BoDeonFactoryService boDeonFactoryService;
	
	@Autowired
	private BoRepository boRepository;
	
	@PostMapping("/salvarBo")
	public ResponseEntity<?> salvarOcorrencia(@RequestBody BoEnvolvidosRequest boEnvolvidosRequest){
		boDeonFactoryService.salvarBo(boEnvolvidosRequest.getBo(), boEnvolvidosRequest.getEnvolvidos());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/buscarPorId/{idBo}")
	public BoDtoResponse buscarBoPorId(@PathVariable(value = "idBo" ) Integer idBo){
		Optional<BoDeon> bo = boService.findById(idBo);
		return boService.boDeonToBoDeonResponse(bo.get());
	}

	@GetMapping("/analisarBoPorId/{idBo}")
	public ResponseEntity<?> analiseBoPorId(@PathVariable(value = "idBo" ) Integer idBo, 
			@RequestParam(value = "status") Status status){
		Optional<BoDeon> bo = boService.findById(idBo); 
		if (bo.isPresent()) {
			boService.mudaStatusBoEmAnalise(bo.get(), status);
			BoDtoResponse boFound = boService.boDeonToBoDeonResponse(bo.get());
			return ResponseEntity.status(HttpStatus.OK).body(boFound);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum boletim de ocorrência encontrado com o ID informado!");
	}
	
	@GetMapping("/verificaBoEmAnalise")
	public ResponseEntity<?> verificaBoEmAnalise(@RequestParam(name = "idBo") Integer idBo){
		var boEmAnalise = boService.verificaBoEmAnalise(idBo);
		if (boEmAnalise) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Boletim de Ocorrência já está em analise por outro analista!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(boEmAnalise);
	}
	
	@PatchMapping("/cancelarAnalise/{idBo}")
	public ResponseEntity<?> mudaStatusBo(@PathVariable(value = "idBo") Integer idBo,
			@RequestBody BoDeon boRequest){
		Optional<BoDeon> bo = boRepository.findById(idBo);
		if (bo.isPresent()) {
			boService.mudaStatusBoEmAnalise(bo.get(), boRequest.getStatus());
		}
		return ResponseEntity.ok("Analise cancelada!");
	}

	/**
	 * @return busca bos que o usuario criou
	 * 
	 * */
	@GetMapping("/buscar")
	public Page<BosPessoaResponseDto> buscar (@RequestParam(name = "idPessoa") Integer idPessoa,
			@PageableDefault(size = 10, page = 0, sort = "idBo", direction = Sort.Direction.ASC) Pageable pageable){
		return boService.buscarBosPessoa(idPessoa, pageable);
	}
}
