package com.pessoaDeon.api.controller.analista;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.dto.integracao.BoResponseDto;
import com.pessoaDeon.domain.model.dto.integracao.RequestDto;
import com.pessoaDeon.domain.service.analista.AnalistaService;
import com.pessoaDeon.domain.service.bo.BoService;
import com.pessoaDeon.domain.service.envolvido.EnvolvidoService;
import com.pessoaDeon.domain.service.integracao.IntegracaoService;

@RestController
@RequestMapping("/api/v1/analiseBo")
public class AnaliseBoController {

	@Value("${url.api-integracao}")
	private String URL;
	
	@Autowired
	private BoService boService;
	
	@Autowired
	private IntegracaoService integracaoService;
	
	@Autowired
	private EnvolvidoService envolvidoService;
	
	@GetMapping
	public ResponseEntity<?> enviarBoSigma(@RequestParam("idBo") Integer idBo, 
			@RequestParam("idAnalista") Integer idAnalista,
			@RequestParam(value = "token") String token) throws IllegalAccessException{
		Optional<BoDeon> bo = boService.findById(idBo);
		var envolvimentos = envolvidoService.getEnvolvimentosBo(idBo);
		RequestDto request = integracaoService.dadosBoToDto(bo.get(), envolvimentos, idAnalista);
		integracaoService.enviaBoSigma(request, token);
		return ResponseEntity.status(HttpStatus.OK).body("BO enviado com sucesso!");
	}
	
}
