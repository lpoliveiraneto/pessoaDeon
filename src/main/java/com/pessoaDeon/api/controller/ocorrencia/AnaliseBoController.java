package com.pessoaDeon.api.controller.ocorrencia;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.dto.integracao.BoResponseDto;
import com.pessoaDeon.domain.model.dto.integracao.RequestDto;
import com.pessoaDeon.domain.service.analista.BoAnaliseService;
import com.pessoaDeon.domain.service.bo.BoService;
import com.pessoaDeon.domain.service.envolvido.EnvolvidoService;
import com.pessoaDeon.domain.service.integracao.IntegracaoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/analiseBo")
public class AnaliseBoController {

	@Autowired
	private BoService boService;
	
	@Autowired
	private IntegracaoService integracaoService;
	
	@Autowired
	private EnvolvidoService envolvidoService;
	
	@Autowired
	private BoAnaliseService boAnaliseService;
	
	@GetMapping
	public ResponseEntity<?> getDadosBo(@RequestParam("idBo") Integer idBo,
			@RequestParam("idAnalista") Integer idAnalista) throws IllegalAccessException{
		Optional<BoDeon> bo = boService.findById(idBo);
		var envolvimentos = envolvidoService.getEnvolvimentosBo(idBo);
		RequestDto request = integracaoService.dadosBoToDto(bo.get(), envolvimentos, idAnalista);
		if (request == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao recuperar dados do Boletim de Ocorrência!"); 
		}
		return ResponseEntity.status(HttpStatus.OK).body(request);
	}
	
	@PostMapping
	public ResponseEntity<?> enviarBoSigma(@RequestBody RequestDto request, HttpServletRequest http, Integer idResposta){
		boAnaliseService.aprovaBoEmAnalise(request.getBoDto(), idResposta);
		BoResponseDto response = integracaoService.enviaBoSigma(request, http);
		if (response == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar Boletim de Ocorrência no SIGMA!");
		}
		var atualizaBo = integracaoService.atualizaNumeroStatusBo(response);
		if (atualizaBo == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar número e status do Boletim de Ocorrência!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("BO enviado com sucesso!");
	}
	
}
