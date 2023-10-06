package com.pessoaDeon.api.controller.analista;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.dto.BoRequestAnaliseDto;
import com.pessoaDeon.domain.model.dto.integracao.BoResponseDto;
import com.pessoaDeon.domain.model.dto.integracao.RequestDto;
import com.pessoaDeon.domain.service.bo.BoService;
import com.pessoaDeon.domain.service.envolvido.EnvolvidoService;
import com.pessoaDeon.domain.service.integracao.IntegracaoService;

@RestController
@RequestMapping("/api/v1/analiseBo")
public class AnaliseBoController {

	@Autowired
	private BoService boService;
	
	@Autowired
	private IntegracaoService integracaoService;
	
	@Autowired
	private EnvolvidoService envolvidoService;
	
	@GetMapping
	public ResponseEntity<?> enviarBoSigma(@RequestBody BoRequestAnaliseDto boAnalise) throws IllegalAccessException{
		
		Optional<BoDeon> bo = boService.findById(boAnalise.getIdBo());
		var envolvimentos = envolvidoService.getEnvolvimentosBo(boAnalise.getIdBo());
		
		RequestDto request = integracaoService.dadosBoToDto(bo.get(), envolvimentos, boAnalise.getIdAnalista());
		if (request == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao recuperar dados do Boletim de Ocorrência!"); 
		}
		
//		BoResponseDto response = integracaoService.enviaBoSigma(request, token);
//		if (response == null) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar Boletim de Ocorrência no SIGMA!");
//		}
//		
//		var atualizaBo = integracaoService.atualizaNumeroStatusBo(response);
//		if (atualizaBo != null) {
//			return ResponseEntity.status(HttpStatus.OK).body("BO enviado com sucesso!");
//		}
		
		return ResponseEntity.status(HttpStatus.OK).body(request);
	}
	
}
